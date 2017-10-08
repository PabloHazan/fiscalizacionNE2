package fiscalizacionne

import command.EscuelaCommand
import command.MesaEscuelaCommand

import static org.springframework.http.HttpStatus.*

class EscuelaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def escuelaService
    def usuarioService
    def comunaService

    def index() {
        Set<Escuela> escuelas
        if(usuarioService.loggeUserIsAdmin()){
            escuelas = Escuela.all
        } else {
            Fiscal adminComuna = usuarioService.getLoggedUser()
            Comuna comuna = comunaService.getByAdministrador(adminComuna)
            escuelas = comuna.escuelas
        }
        [escuelas: escuelas]
    }

    def show(Long id) {
        EscuelaCommand escuela = escuelaService.get(id)
        [escuela: escuela]
    }

    def create() {
        EscuelaCommand escuela = new EscuelaCommand()
        [escuela: escuela]
    }

    def save() {
        EscuelaCommand escuela = new EscuelaCommand()
        bindEscuela(escuela, params)
        if (escuela.hasErrors()) {
            respond escuela.errors, view:'create'
            return
        }
        try{
            Long id = escuelaService.save(escuela)
            flash.message = message(code: 'default.created.message', args: [message(code: 'escuela.label', default: 'Escuela'), escuela.id])
            redirect(action: 'show', id: id)
        }catch (Exception e){
            log.error e.message
            render(view:'create', model: [escuela: escuela])
        }
    }

    def edit(Long id) {
        EscuelaCommand escuela = escuelaService.get(id)
        [escuela: escuela]
    }

    def update() {
        EscuelaCommand escuela = new EscuelaCommand()
        bindEscuela(escuela, params)
        if (escuela.hasErrors()) {
            render([view: 'edit', model: [escuela: escuela]])
            return
        }
        try {
            escuelaService.update(escuela)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'escuela.label', default: 'Escuela'), escuela.id])
            redirect(action: 'show', id: escuela.id)
        }catch (Exception e){
            log.error e.message
            render([view: 'edit', model: [escuela: escuela]])
        }
    }

    def delete(Escuela escuela) {
        if (escuela == null) {
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

    def mesaRow(){
        Long index = params.index.toLong()
        render(template: 'mesaRow', model: [index:index, modoEdicion: true])
    }

    private void bindEscuela(EscuelaCommand escuela, Map params){
        escuela.id = params.id ? params.id.toLong() : null
        escuela.versionValue = params.versionValue ? params.versionValue?.toLong() : null

        escuela.numero = params.numero ? params.numero?.toLong() : null
        bindMesas(escuela.mesas, params.mesas)

        if(params?.fuerzaPoliticaId?.isNumber())
            escuela.fuerzaPolitica = FuerzaPolitica.get(params.fuerzaPoliticaId.toLong())
        if(params?.comunaId?.isNumber())
            escuela.comuna = Comuna.get(params.comunaId.toLong())
    }

    private void bindMesas(List<MesaEscuelaCommand> mesas, Map map){
        for(elem in  map?.keySet().findAll {it.toString().isNumber()}) {
            MesaEscuelaCommand escuela = new MesaEscuelaCommand()
            bindMesa(escuela,map[elem])
            mesas.add(escuela)
        }
    }

    private void bindMesa(MesaEscuelaCommand mesa, Map params){
        mesa.id = params.id? params.id?.toLong() : null
        mesa.versionValue = params.versionValue? params.versionValue?.toLong() : null
        mesa.numero = params.numero? params.numero?.toLong() : null
    }

}
