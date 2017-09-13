package fiscalizacionne

import command.FiscalCommand
import org.hibernate.id.GUIDGenerator

class FiscalService {

    static transactional = true

    def crearFiscal(FiscalCommand fiscalCommand){
        Fiscal fiscal = new Fiscal()

        fiscal.username = fiscalCommand.username
        fiscal.passwordExpired = false
        fiscal.accountExpired = false
        fiscal.accountLocked = false
        fiscal.enabled = true
        fiscal.password =  UUID.randomUUID().toString()

        fiscal.save(failOnError: true)
        Rol rol = Rol.findByAuthority(fiscalCommand.tipoFiscal.authority)
        FiscalRol fiscalRol = new FiscalRol()
        fiscalRol.rol = rol
        fiscalRol.fiscal = fiscal
        fiscalRol.save(failOnError: true, flush:true)
        println fiscalRol.id
        return fiscal.id
    }
}
