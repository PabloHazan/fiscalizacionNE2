package fiscalizacionne

import command.EscuelaCommand
import command.EscuelaComunaCommand
import command.MesaEscuelaCommand


class EscuelaService {

    def mesaService
    def usuarioService
    def comunaService

    EscuelaComunaCommand getEscuelaComuna(Long id){
        Escuela escuela = Escuela.get(id)
        EscuelaComunaCommand escuelaCommand = new EscuelaComunaCommand()
        escuelaCommand.id = escuela.id
        escuelaCommand.versionValue = escuela.version
        escuelaCommand.numero = escuela.numero
        return escuelaCommand
    }

    List<EscuelaComunaCommand> getEscuelaComuna(List<Long> ids) {
        List<EscuelaComunaCommand> escuelas = []
        ids.each {id -> escuelas.add(getEscuelaComuna(id))}
        return escuelas
    }

    List<Long> save(List<EscuelaComunaCommand> escuelasCommand){
        List<Long> ids = []
        escuelasCommand.each {escuelaCommand->
            ids.add(save(escuelaCommand))
        }
        return ids
    }

    Long save(EscuelaComunaCommand escuelaCommand){
        Escuela escuela = new Escuela()
        escuela.numero = escuelaCommand.numero
        escuela.comuna = escuelaCommand.comuna
        escuela.save(failOnError: true)
        escuelaCommand.id = escuela.id
        escuelaCommand.versionValue = escuela.version
        return escuela.id
    }

    EscuelaCommand get(id){
        Escuela escuela = Escuela.get(id)
        EscuelaCommand escuelaCommand = new EscuelaCommand()

        escuelaCommand.id = escuela.id
        escuelaCommand.versionValue = escuela.version
        escuelaCommand.numero = escuela.numero
        escuelaCommand.mesas.addAll(mesaService.get(escuela.mesas*.id.toList()))
        escuelaCommand.fuerzaPolitica = escuela.fuerzaPolitica
        escuelaCommand.comuna = escuela.comuna

        return escuelaCommand
    }

    Long save(EscuelaCommand escuelaCommand){
        Escuela escuela = new Escuela()
        escuela.numero = escuelaCommand.numero
        if (usuarioService.loggeUserIsAdmin()){
            escuela.comuna = escuelaCommand.comuna
        } else {
            Fiscal adminComuna = usuarioService.getLoggedUser()
            Comuna comuna = comunaService.getByAdministrador(adminComuna)
            escuela.comuna = comuna
        }
        escuela.fuerzaPolitica = escuelaCommand.fuerzaPolitica
        escuela.save(failOnError: true)
        escuelaCommand.mesas*.escuela = escuela
        List<Long> mesasIds = mesaService.save(escuelaCommand.mesas)
        Mesa.findAllByIdInList(mesasIds).each {mesa->
            escuela.addToMesas(mesa)
        }
        escuela.save(flush: true, failOnError: true)
        escuelaCommand.id = escuela.id
        escuelaCommand.versionValue = escuela.version
        return escuela.id
    }

    Long update(EscuelaCommand escuelaCommand){
        Escuela escuela = Escuela.get(escuelaCommand.id)
        if(escuela.version != escuelaCommand.versionValue){
            throw new Exception("Escuela desactualizada")
        }
        escuela.numero = escuelaCommand.numero
        escuela.fuerzaPolitica = escuelaCommand.fuerzaPolitica
        escuela.save(failOnError: true)


        def mesasEliminadas = escuela.mesas.findAll{ mesa ->
            !escuelaCommand.mesas.any { mesaCommand -> mesaCommand.id == mesa.id }
        }
        mesasEliminadas.each { mesa -> escuela.removeFromMesas(mesa) }
        mesasEliminadas*.delete(flush: true)

        List<MesaEscuelaCommand> mesasNuevas = escuelaCommand.mesas.findAll { !it.id }
        escuelaCommand.mesas*.escuela = escuela
        mesaService.save(mesasNuevas)

        escuela.save(flush: true, failOnError: true)
        escuelaCommand.id = escuela.id
        escuelaCommand.versionValue = escuela.version
        return escuela.id
    }

}
