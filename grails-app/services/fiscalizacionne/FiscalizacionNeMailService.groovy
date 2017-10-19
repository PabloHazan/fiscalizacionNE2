package fiscalizacionne

import grails.web.mapping.LinkGenerator

class FiscalizacionNeMailService {

    def mailService
    LinkGenerator grailsLinkGenerator

    def enviarMailNuevoFiscal(String mailTo, String username, String password){
        mailService.sendMail {
            to mailTo
            subject "Nuevo usuario"
            text "Su usuario a sido creado con excito. Usuario: $username password: $password . Para cambiar tu contraseña entra ${grailsLinkGenerator.link(controller: 'fiscal', action: 'editPass', absolute:true)}"
        }
    }

}
