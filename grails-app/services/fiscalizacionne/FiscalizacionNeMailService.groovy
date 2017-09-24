package fiscalizacionne

class FiscalizacionNeMailService {

    def mailService

    def enviarMailNuevoFiscal(String mailTo, String username, String password){
        mailService.sendMail {
            to mailTo
            subject "Nuevo usuario"
            text "Su usuario a sido creado con excito. Usuario: $username password: $password"
        }
    }

}
