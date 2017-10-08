package command

import fiscalizacionne.FuerzaPolitica
import grails.validation.Validateable

/**
 * Created by pablo on 18/09/17.
 */
class ComunaCommand implements Validateable{
    Long id
    Long versionValue
    Long numero
    FiscalCommand admin
    List<EscuelaComunaCommand> escuelas
    List<FuerzaPolitica> fuerzasPoliticas

    public ComunaCommand(){
        escuelas = []
        fuerzasPoliticas = []
    }

    static constraints = {
        id nullable: true, blank:true
        versionValue nullable: true, blank:true
    }
}
