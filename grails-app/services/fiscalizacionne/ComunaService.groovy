package fiscalizacionne

import command.ComunaCommand
import command.EscuelaComunaCommand

class ComunaService {

    def escuelaService
    def fiscalService
    def fuerzaPoliticaService

    ComunaCommand get(Long id) {
        Comuna comuna = Comuna.get(id)
        ComunaCommand comunaCommand = new ComunaCommand()
        comunaCommand.id = comuna.id
        comunaCommand.versionValue = comuna.version
        comunaCommand.numero = comuna.numero
        comunaCommand.escuelas = escuelaService.getEscuelaComuna((comuna.escuelas*.id).toList())
        comunaCommand.fuerzasPoliticas.addAll(comuna.fuerzasPoliticas)
        comunaCommand.admin = fiscalService.getFiscal(comuna.admin.id)
        return comunaCommand
    }

    Long save(ComunaCommand comunaCommand){
        Comuna comuna = new Comuna()
        comuna.numero = comunaCommand.numero
        Long adminId = fiscalService.crearAdminComuna(comunaCommand.admin)
        comuna.admin = Fiscal.get(adminId)
        comuna.save(failOnError:true)

        comunaCommand.escuelas*.comuna = comuna
        List<Long> escuelasId = escuelaService.save(comunaCommand.escuelas)
        escuelasId.each {Long escuelaId->
            Escuela escuela = Escuela.get(escuelaId)
            comuna.addToEscuelas(escuela)
        }

        fuerzaPoliticaService.save(comunaCommand.fuerzasPoliticas)
        comunaCommand.fuerzasPoliticas.each { fuerzaPolitica -> comuna.addToFuerzasPoliticas(fuerzaPolitica) }
        comuna.save(flush: true, failOnError: true)
        comunaCommand.id = comuna.id
        comunaCommand.versionValue = comuna.version

        return comuna.id
    }

    Long update(ComunaCommand comunaCommand){
        Comuna comuna = Comuna.get(comunaCommand.id)
        if (comuna.version != comunaCommand.versionValue)
            throw new Exception("comuna desactualizada, recargue la pantalla para poder modificarla")
        def escuelasEliminadas = comuna.escuelas.findAll{ escuela ->
            !comunaCommand.escuelas.any { escuelaCommand -> escuelaCommand.id == escuela.id }
        }
        escuelasEliminadas.each { escuela -> comuna.removeFromEscuelas(escuela) }
        escuelasEliminadas*.delete(flush: true)

        List<EscuelaComunaCommand> escuelasNuevas = comunaCommand.escuelas.findAll { !it.id }
        escuelasNuevas*.comuna = comuna
        escuelaService.save(escuelasNuevas)

        fuerzaPoliticaService.save(comunaCommand.fuerzasPoliticas.findAll {fuerzaPolitica -> !fuerzaPolitica.id})
        def fuerzasAgregadas = comunaCommand.fuerzasPoliticas.findAll {fuerzaPolitica ->
            !comuna.fuerzasPoliticas.any {f -> f.id == fuerzaPolitica.id}
        }
        fuerzasAgregadas.each { fuerzaPolitica -> comuna.addToFuerzasPoliticas(fuerzaPolitica) }
        def fuerzasEliminadas = comuna.fuerzasPoliticas.findAll { fuerzaPolitica ->
            !comunaCommand.fuerzasPoliticas.any {f -> f.id == fuerzaPolitica.id }
        }
        fuerzasEliminadas.each {fuerzaPolitica -> comuna.removeFromFuerzasPoliticas(fuerzaPolitica)}
        comuna.save(flush: true, failOnError:true)
        return comuna.id

    }

    Comuna getByAdministrador(Fiscal fiscal){
        if (!fiscal)
            return null
        Comuna comuna = Comuna.findByAdmin(fiscal)
        return comuna
    }
}
