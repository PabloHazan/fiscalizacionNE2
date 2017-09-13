package fiscalizacionne

import command.FiscalCommand

import static org.springframework.http.HttpStatus.*

class FiscalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def fiscalService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Fiscal.list(params), model:[fiscalCount: Fiscal.count()]
    }

    def show(Fiscal fiscal) {
        respond fiscal
    }

    def create() {
        respond new FiscalCommand()
    }

    def save(FiscalCommand fiscal) {
        println fiscal.username
        println fiscal.tipoFiscal.authority
        println fiscal.mail
        if (fiscal == null) {
            notFound()
            return
        }

        if (fiscal.hasErrors()) {
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

    def edit(Fiscal fiscal) {
        respond fiscal
    }

    def update(Fiscal fiscal) {
        if (fiscal == null) {
            notFound()
            return
        }

        if (fiscal.hasErrors()) {
            respond fiscal.errors, view:'edit'
            return
        }

        fiscal.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fiscal.label', default: 'Fiscal'), fiscal.id])
                redirect fiscal
            }
            '*'{ respond fiscal, [status: OK] }
        }
    }

    def delete(Fiscal fiscal) {

        if (fiscal == null) {
            notFound()
            return
        }

        fiscal.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fiscal.label', default: 'Fiscal'), fiscal.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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
}
