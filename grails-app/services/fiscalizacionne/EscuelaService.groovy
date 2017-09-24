package fiscalizacionne

import command.EscuelaCommand


class EscuelaService {

    def mesaService

    EscuelaCommand get(Long id){
        Escuela escuela = Escuela.get(id)
        EscuelaCommand escuelaCommand = new EscuelaCommand()
        escuelaCommand.id = escuela.id
        escuelaCommand.versionValue = escuela.version
        escuelaCommand.numero = escuela.numero
        return escuelaCommand
    }

    List<EscuelaCommand> get(List<Long> ids) {
        List<EscuelaCommand> escuelas = []
        ids.each {id -> escuelas.add(get(id))}
        return escuelas
    }

    List<Long> save(List<EscuelaCommand> escuelasCommand){
        List<Long> ids = []
        escuelasCommand.each {escuelaCommand->
            ids.add(save(escuelaCommand))
        }
        return ids
    }

    Long save(EscuelaCommand escuelaCommand){
        Escuela escuela = new Escuela()
        escuela.numero = escuelaCommand.numero
        escuela.comuna = escuelaCommand.comuna
        escuela.save(failOnError: true)
        escuelaCommand.id = escuela.id
        escuelaCommand.versionValue = escuela.version
        return escuela.id
    }

}
