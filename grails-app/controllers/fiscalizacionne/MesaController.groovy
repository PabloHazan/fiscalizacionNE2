package fiscalizacionne

import static org.springframework.http.HttpStatus.*

class MesaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def mesaService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Mesa.list(params), model:[mesaCount: Mesa.count()]
    }

    def show(Mesa mesa) {
        respond mesa
    }

    def create() {
        respond new Mesa(params)
    }

    def save(Mesa mesa) {
        mesa.numero = params.numero.toLong()
        println mesa.numero
        println mesa.numero
        println mesa.numero
        println mesa.numero
        if (mesa == null) {
            notFound()
            return
        }

        if (mesa.hasErrors()) {
            respond mesa.errors, view:'create'
            return
        }

        try{
            mesaService.crearMesa(mesa)
            redirect(action: 'show', id:mesa.id ,model: [mesa:mesa, status: CREATED])
        } catch (Exception e){
            render(view: 'create', model: [mesa:mesa])
        }

    }

    def edit(Mesa mesa) {
        respond mesa
    }

    def update(Mesa mesa) {
        if (mesa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (mesa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mesa.errors, view:'edit'
            return
        }

        mesa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'mesa.label', default: 'Mesa'), mesa.id])
                redirect mesa
            }
            '*'{ respond mesa, [status: OK] }
        }
    }

    def delete(Mesa mesa) {

        if (mesa == null) {
            notFound()
            return
        }

        mesaService.borrarMesa(mesa)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'mesa.label', default: 'Mesa'), mesa.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesa.label', default: 'Mesa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
