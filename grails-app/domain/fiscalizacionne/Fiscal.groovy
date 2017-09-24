package fiscalizacionne

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Fiscal implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    String mail

    Set<Rol> getAuthorities() {
        (FiscalRol.findAllByFiscal(this) as List<FiscalRol>)*.rol as Set<Rol>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
	    password column: '`password`'
    }
}
