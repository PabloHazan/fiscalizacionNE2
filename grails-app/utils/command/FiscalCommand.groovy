package command

import grails.validation.Validateable

/**
 * Created by pablo on 12/09/17.
 */

class FiscalCommand implements Validateable {
    Long id
    Long versionValue
    String username
    String mail

    static constraints = {
        id nullable: true
        versionValue nullable:true
        username nullable: false, blank: false
        mail nullable: false, blank: false, mail:true

    }

}
