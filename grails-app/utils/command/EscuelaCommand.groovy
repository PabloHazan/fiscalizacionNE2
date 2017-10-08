package command

import fiscalizacionne.Comuna
import fiscalizacionne.FuerzaPolitica
import grails.validation.Validateable

/**
 * Created by pablo on 24/09/17.
 */
class EscuelaCommand implements Validateable{
    Long id
    Long versionValue

    Long numero
    List<MesaEscuelaCommand> mesas
    FuerzaPolitica fuerzaPolitica
    Comuna comuna

    public EscuelaCommand(){
        this.mesas = []
    }
}
