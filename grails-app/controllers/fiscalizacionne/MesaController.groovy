package fiscalizacionne

import static org.springframework.http.HttpStatus.*

class MesaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def mesaService
    def fiscalService

    def index(Integer max) {
        [mesas: Mesa.all]
    }

    def show(Mesa mesa) {
        respond mesa
    }

    def create() {
        [mesa: new Mesa(), fiscales: fiscalService.getFiscalesDisponibles()]
    }

    def save() {
        Mesa mesa = new Mesa(params)
        bindMesa(mesa)
        mesa.validate()
        if (mesa.hasErrors()) {
            respond mesa.errors, view:'create'
            return
        }

        try{
            mesaService.crearMesa(mesa)
            redirect(action: 'show', id:mesa.id ,model: [mesa:mesa, status: CREATED])
        } catch (Exception e){
            log.error(e.message)
            render(view: 'create', model: [mesa:mesa])
        }

    }

    def edit(Mesa mesa) {
        List<Fiscal> fiscales = fiscalService.getFiscalesDisponibles()
        if (mesa.fiscal){
            fiscales << mesa.fiscal
        }
        [mesa: mesa, fiscales: fiscales]
    }

    def update(Mesa mesa) {
        if (mesa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        //Todo: si cambio el fiscal al anterior asingarle null en tipo
//        Fiscal fiscalAnterior = Mesa.get(mesa.id).fiscal
//        FiscalRol.findByFiscal(fiscalAnterior)?.delete(flush: true)

        bindMesa(mesa)
        mesa.validate()
        if (mesa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mesa.errors, view:'edit'
            return
        }
        if(mesa.fiscal)
            fiscalService.desasignarFiscalizacion(mesa.fiscal?.id)
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
        try {
            mesaService.borrarMesa(mesa)
            redirect(action: 'index')
        }catch (Exception e){
            log.error(e.message)
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

    private void bindMesa(Mesa mesa) {
        mesa.escuela = Escuela.get(params.getLong("escuelaId"))
        mesa.fiscal = Fiscal.get(params.getLong("fiscalId"))
    }
}
