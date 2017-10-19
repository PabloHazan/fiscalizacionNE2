package fiscalizacionne

import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {

    def init = { servletContext ->
        SpringSecurityUtils.clientRegisterFilter("corsFilterFiscalizacion",
                SecurityFilterPosition.SECURITY_CONTEXT_FILTER.order - 1)
    }

    def destroy = {
    }
}
