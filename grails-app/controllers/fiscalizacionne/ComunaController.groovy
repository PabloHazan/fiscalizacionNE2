package fiscalizacionne

import command.ComunaCommand
import command.EscuelaComunaCommand
import command.FiscalCommand

import static org.springframework.http.HttpStatus.*

class ComunaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def comunaService
    def usuarioService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [comunas: Comuna.all, comunaCount: Comuna.count]
    }

    def show(Long id) {
        ComunaCommand comuna = comunaService.get(id)
        if(usuarioService.isAdminOrAdminComunaValido(comuna.admin.id)){
            notFound()
            return
        }
        [comuna: comuna]
    }

    def create() {
        ComunaCommand comuna = new ComunaCommand()
        [comuna: comuna]
    }

    def save(ComunaCommand comuna) {
        if (comuna == null) {
            notFound()
            return
        }

        bindComuna(comuna, params)

        if (comuna.hasErrors()) {
            render(view: 'create', model: [comuna: comuna])
            return
        }

        try {
            Long comunaId = comunaService.save(comuna)
            flash.message = message(code: 'default.created.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id])
            redirect(action: 'show', id: comunaId)
        }catch (Exception e){
            log.error e.message
            render(view: 'create', model: [comuna: comuna])
        }

    }

    def edit(Long id) {
        ComunaCommand comuna = comunaService.get(id)
        if(usuarioService.isAdminOrAdminComunaValido(comuna.admin.id)){
            notFound()
            return
        }
        [comuna: comuna]
    }

    def update(ComunaCommand comuna) {
        if(usuarioService.isAdminOrAdminComunaValido(comuna.admin.id)){
            notFound()
            return
        }
        if (comuna == null) {
            notFound()
            return
        }
        bindComuna(comuna, params)
        if (comuna.hasErrors()) {
            render(view:"edit", model:[comuna: comuna, modoEdicion: true])
            return
        }

        try {
            Long id = comunaService.update(comuna)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id])
            redirect(action: "show", id: id)
        }catch (Exception e){
            println e.message
            log.error(e.message)
            render(view:"edit", model:[comuna: comuna, modoEdicion: true])
        }
    }

    def delete(Comuna comuna) {

        if (comuna == null) {
            notFound()
            return
        }

        comuna.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'comuna.label', default: 'Comuna'), comuna.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comuna.label', default: 'Comuna'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def escuelaRow(){
        Long index = params.index.toLong()
        render(template: 'escuelaRow', model: [index:index, modoEdicion: true])
    }

    def fuerzaPoliticaRow(){
        Long index = params.index.toLong()
        render(template: 'fuerzaPoliticaRow', model: [index:index, modoEdicion: true])
    }


    private void bindComuna(ComunaCommand comuna, Map params){
        comuna.id = params.id ? params.id.toLong() : null
        comuna.versionValue = params.versionValue ? params.versionValue?.toLong() : null

        comuna.numero = params.numero ? params.numero?.toLong() : null
        bindAdmin(comuna.admin, params.admin)
        bindEscuelas(comuna.escuelas, params.escuelas)
        bindFuerzasPoliticas(comuna.fuerzasPoliticas, params.fuerzasPoliticas)
    }

    private void bindAdmin(FiscalCommand admin, Map params){
        admin.id = params.id ? params.id?.toLong() : null
        admin.versionValue= params.versionValue ? params.versionValue?.toLong() : null

        admin.username = params.username ?: null
        admin.mail = params.mail ?: null
    }

    private void bindEscuelas(List<EscuelaComunaCommand> escuelas, Map map){
        for(elem in  map?.keySet().findAll {it.toString().isNumber()}) {
            EscuelaComunaCommand escuela = new EscuelaComunaCommand()
            bindEscuela(escuela,map[elem])
            escuelas.add(escuela)
        }
    }

    private void bindEscuela(EscuelaComunaCommand escuela, Map params){
        escuela.id = params.id? params.id?.toLong() : null
        escuela.versionValue = params.versionValue? params.versionValue?.toLong() : null
        escuela.numero = params.numero? params.numero?.toLong() : null
    }

    private void bindFuerzasPoliticas(List<FuerzaPolitica> fuerzasPoliticas, Map map){
        for(elem in  map?.keySet().findAll {it.toString().isNumber()}) {
            if(map[elem]["cbPertenece"] == "on"){
                FuerzaPolitica fuerzaPolitica = new FuerzaPolitica()
                bindFuerzaPolitica(fuerzaPolitica, map[elem])
                fuerzasPoliticas.add(fuerzaPolitica)
            }
        }
    }

    private void bindFuerzaPolitica(FuerzaPolitica fuerzaPolitica, Map params){
        fuerzaPolitica.id = params.id? params.id?.toLong() : null
        fuerzaPolitica.version = params.version? params.version?.toLong() : null
        fuerzaPolitica.nombre = params.nombre ?: null
        fuerzaPolitica.color = params.color?.isNumber() ? params.color.toLong() : null
    }
}
