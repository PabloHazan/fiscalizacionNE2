package fiscalizacionne

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class FiscalRol implements Serializable {

	private static final long serialVersionUID = 1

	Fiscal fiscal
	Rol rol

	@Override
	boolean equals(other) {
		if (other instanceof FiscalRol) {
			other.fiscalId == fiscal?.id && other.rolId == rol?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (fiscal) {
            hashCode = HashCodeHelper.updateHash(hashCode, fiscal.id)
		}
		if (rol) {
		    hashCode = HashCodeHelper.updateHash(hashCode, rol.id)
		}
		hashCode
	}

	static FiscalRol get(long fiscalId, long rolId) {
		criteriaFor(fiscalId, rolId).get()
	}

	static boolean exists(long fiscalId, long rolId) {
		criteriaFor(fiscalId, rolId).count()
	}

	private static DetachedCriteria criteriaFor(long fiscalId, long rolId) {
		FiscalRol.where {
			fiscal == Fiscal.load(fiscalId) &&
			rol == Rol.load(rolId)
		}
	}

	static FiscalRol create(Fiscal fiscal, Rol rol, boolean flush = false) {
		def instance = new FiscalRol(fiscal: fiscal, rol: rol)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(Fiscal u, Rol r) {
		if (u != null && r != null) {
			FiscalRol.where { fiscal == u && rol == r }.deleteAll()
		}
	}

	static int removeAll(Fiscal u) {
		u == null ? 0 : FiscalRol.where { fiscal == u }.deleteAll() as int
	}

	static int removeAll(Rol r) {
		r == null ? 0 : FiscalRol.where { rol == r }.deleteAll() as int
	}

	static constraints = {
	    fiscal nullable: false
		rol nullable: false, validator: { Rol r, FiscalRol ur ->
			if (ur.fiscal?.id) {
				if (FiscalRol.exists(ur.fiscal.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['fiscal', 'rol']
		version false
	}
}
