package fiscalizacionne

import command.FiscalCommand
import fiscalizacionne.TipoFiscalEnum

import static org.springframework.http.HttpStatus.*

class FiscalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def fiscalService
    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        //def query = "from Fiscal as f where not exists(select 1 from FiscalRol as fr where fr.fiscal = f and fr.rol.authority in ('ROLE_ADMIN', 'ROLE_ADMIN_COMUNA'))"
        def fiscalList = fiscalService.getFiscales()
        [fiscalList:fiscalList, fiscalCount: fiscalList.size()]
    }

    def show(FiscalCommand fiscal) {
        fiscal = fiscalService.getFiscal(params.id?.toLong())
        [fiscal: fiscal]
    }

    def create() {
        [fiscal: new FiscalCommand()]
    }

    def save(FiscalCommand fiscal) {
        if (fiscal == null) {
            notFound()
            return
        }

        if (fiscal.hasErrors()) {
            log.error fiscal.errors
            respond fiscal.errors, view:'create'
            return
        }
        try {
            Long id = fiscalService.crearFiscal(fiscal)
            flash.message = message(code: 'default.created.message', args: [message(code: 'fiscal.label', default: 'Fiscal'), id])
            redirect(action: 'show', id:id)
        }catch (Exception e){
            log.error e.message
            flash.error = e.message
            render(view:'create', model: [fiscal:fiscal])
        }
    }

    def edit(FiscalCommand fiscal) {
        fiscal = fiscalService.getFiscal(params.id?.toLong())
        [fiscal: fiscal]
    }

    def update(FiscalCommand fiscal) {
        if (fiscal == null) {
            notFound()
            return
        }

        if (fiscal.hasErrors()) {
            respond fiscal.errors, view:'edit'
            return
        }
        try {
            fiscalService.update(fiscal)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'fiscal.label', default: 'Fiscal'), fiscal.id])
            redirect(action: 'show', id: fiscal.id)
        }catch (Exception e){
            log.error e.message
            render(view:'edit', model: [fiscal: fiscal])
        }
    }

    def delete(Long id) {
        fiscalService.delete(id)
        redirect(action: 'index')
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fiscal.label', default: 'Fiscal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def asignarFiscal(){
        fiscalizacionne.TipoFiscalEnum tipoFiscalEnum = fiscalizacionne.TipoFiscalEnum.findByAuthority(params.tipoFiscal)

        try {
            switch (tipoFiscalEnum){
                case fiscalizacionne.TipoFiscalEnum.GENERAL:
                    asignarFiscalEscuela()
                    break
                case fiscalizacionne.TipoFiscalEnum.MESA:
                    asignarFiscalMesa()
                    break
            }
        } catch (Exception e){
            log.error e.message
        }


        redirect(action: 'index')
    }

    private void asignarFiscalMesa(){
        Long idFiscal = params.idFiscalSeleccionado.toLong()
        Long idMesa = params.idMesa.toLong()
        fiscalService.asignarFiscalMesa(idFiscal, idMesa)
        return
    }

    private void asignarFiscalEscuela(){
        Long idFiscal = params.idFiscalSeleccionado.toLong()
        Long idEscuela = params.idEscuela.toLong()
        fiscalService.asignarFiscalGeneral(idFiscal, idEscuela)
        return
    }

    def editPass(Long id){
        Fiscal fiscal = springSecurityService.currentUser
        if(id != fiscal.id){
            redirect(action: 'index')
            return
        }
        [fiscal:fiscalService.getFiscal(fiscal.id)]
    }


    def passwordEncoder

    def updatePass(Long id){
        Fiscal fiscal = Fiscal.get(id)
        String oldPass = params.oldPass
        String newPass = params.newPass
        String repeatPass = params.repeatPass
        if(id != fiscal.id){
            log.error "id invalido"
            redirect(action: 'index')
            return
        }

        if (!newPass.equals(repeatPass) || !passwordEncoder.isPasswordValid(fiscal.password, oldPass, null)){
            log.error "pass vieja invalida: ${!passwordEncoder.isPasswordValid(fiscal.password, oldPass, null)}"
            log.error "pass nueva no coincide ${!newPass.equals(repeatPass)}"
            redirect(action: 'index')
            return
        }
        fiscal.password = newPass
        fiscal.save(failOnError:true, flush:true)
        redirect(action: 'index')
    }
}
