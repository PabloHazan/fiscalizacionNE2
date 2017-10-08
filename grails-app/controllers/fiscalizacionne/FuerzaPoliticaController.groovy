package fiscalizacionne

import static org.springframework.http.HttpStatus.*

class FuerzaPoliticaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        [fuerzasPoliticas: FuerzaPolitica.all]
    }

    def show(FuerzaPolitica fuerzaPolitica) {
        respond fuerzaPolitica
    }

    def create() {
        respond new FuerzaPolitica(params)
    }

    def save(FuerzaPolitica fuerzaPolitica) {
        if (fuerzaPolitica == null) {
            notFound()
            return
        }

        if (fuerzaPolitica.hasErrors()) {
            respond fuerzaPolitica.errors, view:'create'
            return
        }

        fuerzaPolitica.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fuerzaPolitica.label', default: 'FuerzaPolitica'), fuerzaPolitica.id])
                redirect fuerzaPolitica
            }
            '*' { respond fuerzaPolitica, [status: CREATED] }
        }
    }

    def edit(FuerzaPolitica fuerzaPolitica) {
        respond fuerzaPolitica
    }

    def update(FuerzaPolitica fuerzaPolitica) {
        if (fuerzaPolitica == null) {
            notFound()
            return
        }

        if (fuerzaPolitica.hasErrors()) {
            respond fuerzaPolitica.errors, view:'edit'
            return
        }

        fuerzaPolitica.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fuerzaPolitica.label', default: 'FuerzaPolitica'), fuerzaPolitica.id])
                redirect fuerzaPolitica
            }
            '*'{ respond fuerzaPolitica, [status: OK] }
        }
    }

    def delete(FuerzaPolitica fuerzaPolitica) {

        if (fuerzaPolitica == null) {
            notFound()
            return
        }

        fuerzaPolitica.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fuerzaPolitica.label', default: 'FuerzaPolitica'), fuerzaPolitica.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fuerzaPolitica.label', default: 'FuerzaPolitica'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def getSelectFuerzasByComuna(){
        Boolean modoEdicion = params.getBoolean("modoEdicion")
        String name = params.name
        String id = params.id

        Long comunaId = params.comunaId?.isNumber() ? params.comunaId.toLong() : null
        render(template: 'fuerzaPoliticaSelect',
                model: [
                        comunaId: comunaId,
                        modoEdicion: modoEdicion,
                        name: name,
                        id: id
                ])
    }
}
