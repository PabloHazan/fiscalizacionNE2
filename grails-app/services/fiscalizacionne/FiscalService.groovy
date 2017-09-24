package fiscalizacionne

import command.FiscalCommand
import dto.FiscalListDTO
import fiscalizacionne.TipoFiscalEnum
import org.hibernate.id.GUIDGenerator
import org.hibernate.transform.Transformers

class FiscalService {

    static transactional = true

    def sessionFactory
    def fiscalizacionNeMailService

    Long crearFiscal(FiscalCommand fiscalCommand){
        Fiscal fiscal = new Fiscal()

        fiscal.username = fiscalCommand.username
        fiscal.passwordExpired = false
        fiscal.accountExpired = false
        fiscal.accountLocked = false
        fiscal.enabled = true
        String pass = UUID.randomUUID().toString()
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
}
