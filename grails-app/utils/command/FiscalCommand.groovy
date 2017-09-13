package command

import fiscalizacionne.TipoFiscalEnum
import grails.validation.Validateable

/**
 * Created by pablo on 12/09/17.
 */

class FiscalCommand implements Validateable {
    String username
    String mail
    TipoFiscalEnum tipoFiscal

    static constraints = {
        username nullable: false, blank: false
        mail nullable: false, blank: false, mail:true

    }

}
