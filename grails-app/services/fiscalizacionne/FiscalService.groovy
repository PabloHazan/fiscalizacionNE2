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
        }
        return mesas
    }

    void cargarResultados(Fiscal fiscal, List<ResultadoMesaDTO>resultadosMesas){
        //obtengo de las mesas enviadas las que puede cargar el fiscal
        Set<Mesa> mesas = Mesa.findAllByFiscalAndNumeroInList(fiscal, resultadosMesas*.mesa)
        mesas.each {mesa ->
            //consigo la urna de la mesa y los resultados de esa mesa
            Urna urna = Urna.findByMesa(mesa)
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
        }
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

}
