package fiscalizacionne

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class PartidoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def partidoService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Partido.list(params), model:[partidoCount: Partido.count()]
    }

    def show(Partido partido) {
        respond partido
    }

    def create() {
        respond new Partido(params)
    }

    def save(Partido partido) {
        if (partido == null) {
            notFound()
            return
        }

        if (partido.hasErrors()) {
            respond partido.errors, view:'create'
            return
        }

        try {
            partidoService.crearPartido(partido)
            redirect(action: 'show', id:partido.id, model: [partido:partido])
        } catch (Exception e){
            render(view: 'create', model: [partido: partido])
        }
    }

    def edit(Partido partido) {
        respond partido
    }

    def update(Partido partido) {
        if (partido == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (partido.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond partido.errors, view:'edit'
            return
        }

        partido.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'partido.label', default: 'Partido'), partido.id])
                redirect partido
            }
            '*'{ respond partido, [status: OK] }
        }
    }

    def delete(Partido partido) {

        if (partido == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        partido.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'partido.label', default: 'Partido'), partido.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'partido.label', default: 'Partido'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
