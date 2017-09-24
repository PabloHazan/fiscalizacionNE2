package fiscalizacionne

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class EscuelaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Escuela.list(params), model:[escuelaCount: Escuela.count()]
    }

    def show(Escuela escuela) {
        respond escuela
    }

    def create() {
        respond new Escuela(params)
    }

    def save(Escuela escuela) {
        if (escuela == null) {
            notFound()
            return
        }

        if (escuela.hasErrors()) {
            respond escuela.errors, view:'create'
            return
        }

        escuela.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'escuela.label', default: 'Escuela'), escuela.id])
                redirect escuela
            }
            '*' { respond escuela, [status: CREATED] }
        }
    }

    def edit(Escuela escuela) {
        respond escuela
    }

    @Transactional
    def update(Escuela escuela) {
        if (escuela == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (escuela.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond escuela.errors, view:'edit'
            return
        }

        escuela.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'escuela.label', default: 'Escuela'), escuela.id])
                redirect escuela
            }
            '*'{ respond escuela, [status: OK] }
        }
    }

    @Transactional
    def delete(Escuela escuela) {

        if (escuela == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        escuela.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'escuela.label', default: 'Escuela'), escuela.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'escuela.label', default: 'Escuela'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
