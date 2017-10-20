package fiscalizacionne

import command.FiscalCommand
import dto.FiscalListDTO
import dto.response.MesaDTO
import dto.response.ResultadoMesaDTO
import dto.response.ResultadoPartidoMesaDTO
import fiscalizacionne.TipoFiscalEnum
import org.hibernate.id.GUIDGenerator
import org.hibernate.transform.Transformers

class FiscalService {

    static transactional = true

    def sessionFactory
    def fiscalizacionNeMailService
    def usuarioService
    def mesaService

    Long crearFiscal(FiscalCommand fiscalCommand){
        Fiscal fiscal = new Fiscal()

        fiscal.username = fiscalCommand.username
        fiscal.passwordExpired = false
        fiscal.accountExpired = false
        fiscal.accountLocked = false
        fiscal.enabled = true
        String pass = UUID.randomUUID().toString().substring(0,6)
        fiscal.password =  pass
        fiscal.mail = fiscalCommand.mail

        fiscal.save(failOnError: true)

        fiscalCommand.id = fiscal.id
        fiscalCommand.versionValue = fiscal.version
        fiscalizacionNeMailService.enviarMailNuevoFiscal(fiscalCommand.mail, fiscal.username, pass)

        return fiscal.id
    }

    Long crearAdminComuna(FiscalCommand fiscalCommand){
        Long id = crearFiscal(fiscalCommand)
        asignarRol(Fiscal.get(id), "ROLE_ADMIN_COMUNA")
        return id
    }

    Set<FiscalListDTO> getFiscales(){
        def querySinRol = "select distinct f.username as username, f.mail as mail, f.id as id, f.version as versionValue " +
                "from Fiscal as f " +
                "where not exists(" +
                "select 1 from FiscalRol as fr " +
                    "where fr.fiscal.id = f.id" +
                ")"
        def fiscalList = executeQuery(querySinRol, null, [])
        def query = "select distinct new dto.FiscalListDTO(f.id, f.version, f.username, f.mail, r.authority) " +
                "from FiscalRol as fr join fr.fiscal as f join fr.rol as r " +
                "where r.authority not in ('ROLE_ADMIN', 'ROLE_ADMIN_COMUNA')"

        fiscalList.addAll(Fiscal.executeQuery(query))
        return fiscalList
    }

    Set<FiscalListDTO> executeQuery(def queryStr, def map, def paginado){

        final session = sessionFactory.currentSession
        def query = session.createQuery(queryStr)

        if(paginado){
            query.setFirstResult(map["offset"]?.toInteger())
            query.setMaxResults(map["max"])
        }

        query.setResultTransformer(Transformers.aliasToBean(FiscalListDTO.class));

        return query.list();

    }

    FiscalCommand getFiscal(Long id){
        def fiscal = Fiscal.get(id)
        FiscalCommand fiscalCommand = new FiscalCommand()
        fiscalCommand.id = fiscal.id
        fiscalCommand.versionValue = fiscal.version
        fiscalCommand.username = fiscal.username
        fiscalCommand.mail = fiscal.mail
        return fiscalCommand
    }

    def update(FiscalCommand fiscalCommand){
        def fiscal = Fiscal.get(fiscalCommand?.id)
        fiscal.mail = fiscalCommand.mail
        fiscal.save(failOnError: true)
    }

    def delete(Long id){
        def fiscal = Fiscal.get(id)
        fiscal.delete()
    }

    def asignarFiscalMesa(Long idFiscal, Long idMesa){
        Mesa mesa = Mesa.get(idMesa)
        Fiscal fiscal = Fiscal.get(idFiscal)
        mesa.fiscal = fiscal
        mesa.save(failOnError:true)
        asignarRol(fiscal, fiscalizacionne.TipoFiscalEnum.MESA)
    }

    def asignarFiscalGeneral(Long idFiscal, Long idEscuela){
        Escuela escuela = Escuela.get(idEscuela)
        Fiscal fiscal = Fiscal.get(idFiscal)
        escuela.fiscal = fiscal
        escuela.save(failOnError:true)
        asignarRol(fiscal, fiscalizacionne.TipoFiscalEnum.GENERAL)
    }

    def asignarRol(Fiscal fiscal, fiscalizacionne.TipoFiscalEnum tipoFiscal){
        asignarRol(fiscal, tipoFiscal.authority)
    }

    def asignarRol(Fiscal fiscal, String authority){
        Rol rol = Rol.findByAuthority(authority)
        FiscalRol fiscalRol = new FiscalRol()
        fiscalRol.rol = rol
        fiscalRol.fiscal = fiscal
        fiscalRol.save(failOnError: true, flush:true)
    }

    List<MesaDTO> getMesas(Fiscal fiscal){
        List<MesaDTO> mesas = []
        if (usuarioService.isFiscalMesa(fiscal)){
            Mesa.findAllByFiscal(fiscal).each { Mesa mesa ->
                return mesas.add(mesaService.toResponseDTO(mesa))
            }
        }else if (usuarioService.isFiscalGeneral(fiscal)){
            Escuela.findByFiscal(fiscal)?.mesas?.each {mesa ->
                return mesas.add(mesaService.toResponseDTO(mesa))
            }
        }else if(usuarioService.isAdminComuna(fiscal)){
            Comuna comuna = Comuna.findByAdmin(fiscal)
            Escuela.findAllByComuna(comuna).each { escuela ->
                escuela.mesas.each {mesa ->
                    mesas.add(mesaService.toResponseDTO(mesa))
                }
            }
        }
        return mesas
    }

    List<Long> cargarResultados(Fiscal fiscal, List<ResultadoMesaDTO>resultadosMesas){

        List<Long> numeroMesasCargadas = []

        //obtengo de las mesas enviadas las que puede cargar el fiscal
        Set<Mesa> mesas = filtrarMesasValidasPorFiscal(fiscal, resultadosMesas)
        mesas.each {mesa ->
            //consigo la urna de la mesa y los resultados de esa mesa
            Urna urna = Urna.findByMesa(mesa)
            if (puedeCargarUrna(urna, fiscal)){
                List<ResultadoPartidoMesaDTO> resultadosPartidosMesa = resultadosMesas.find { resultadoMesa ->
                    resultadoMesa.mesa = mesa.numero
                }.resultados
                urna.boletas.each {boleta ->
                    //consigo los resultados del partido en la mesa y los cargo como boletas de la urna
                    ResultadoPartidoMesaDTO resultadoPartido = resultadosPartidosMesa.find {resultadoPartidoMesa ->
                        resultadoPartidoMesa.partido == boleta.partido.id
                    }
                    boleta.legisladores = resultadoPartido.legisladores
                    boleta.diputados = resultadoPartido.diputados
                }
                //registro quien cargo la urna
                urna.informante = fiscal
                urna.save(flush: true)
                numeroMesasCargadas << mesa.numero
            }
        }
        return numeroMesasCargadas
    }

    List<Fiscal> getFiscalesDisponibles(){
        String query = "select distinct f from FiscalRol as fr " +
                "join fr.fiscal as f " +
                "join fr.rol as r " +
                "where r.authority in ('ROLE_FISCAL_GENERAL', 'ROLE_FISCAL_MESA') and " +
                "not exists(" +
                    "select 1 from Escuela e " +
                    "join e.mesas m " +
                    "where e.fiscal = f or m.fiscal = f) "
        List<Fiscal> fiscales = Fiscal.findAll(query).toList()
        return fiscales
    }

    void desasignarFiscalizacion(Long fiscalId){
        Fiscal fiscal = Fiscal.get(fiscalId)
        FiscalRol fiscalRol = FiscalRol.findByFiscal(fiscal)
        if(fiscalRol){
            fiscalizacionne.TipoFiscalEnum tipoFiscal = fiscalizacionne.TipoFiscalEnum.findByAuthority(fiscalRol.rol.authority)
            fiscalRol.delete(flush: true)
            switch (tipoFiscal) {
                case fiscalizacionne.TipoFiscalEnum.GENERAL:
                    Escuela escuela = Escuela.findByFiscal(fiscal)
                    escuela.fiscal = null
                    escuela.save(flush: true)
                    break
                case fiscalizacionne.TipoFiscalEnum.MESA:
                    Mesa mesa = Mesa.findByFiscal(fiscal)
                    mesa.fiscal = null
                    mesa.save(flush:true)
                    break
            }
        }
    }

    Boolean puedeCargarUrna(Urna urna, Fiscal fiscal){

        if (!urna.informante) //no esta cargada
            return true
        if (fiscal == urna.informante)//corrige quien cargo
            return true
        if (usuarioService.isFiscalGeneral(fiscal) &&  //es fiscal general
                urna.mesa.escuela.fiscal == fiscal && //de esa escuela
                !usuarioService.isAdminComuna(urna.informante)) //y no la cargo el admin de comuna
            return true
        if(urna.mesa.escuela.comuna.admin == fiscal)
            return true
        return false
    }

    Set<Mesa> filtrarMesasValidasPorFiscal(Fiscal fiscal, List<ResultadoMesaDTO> resultadosMesas){
        if (usuarioService.isFiscalMesa(fiscal))
            return [Mesa.findByFiscalAndNumeroInList(fiscal, resultadosMesas*.mesa)].toSet()
        else if (usuarioService.isFiscalGeneral(fiscal))
            return Escuela.findByFiscal(fiscal).mesas.findAll { mesa ->
                resultadosMesas*.mesa.any{
                    numeroMesa -> numeroMesa == mesa.numero
                }
            }
        else if (usuarioService.isAdminComuna(fiscal)){
            Set<Mesa> mesasValidas = []
            Comuna comuna = Comuna.findByAdmin(fiscal)
            Set<Escuela> escuelas = Escuela.findAllByComuna(comuna)
            escuelas.each {escuela ->
                mesasValidas.addAll(escuela.mesas.findAll { mesa ->
                    resultadosMesas*.mesa.any{
                        numeroMesa -> numeroMesa == mesa.numero
                    }
                })
            }
            return mesasValidas
        }

        return []
    }
}
