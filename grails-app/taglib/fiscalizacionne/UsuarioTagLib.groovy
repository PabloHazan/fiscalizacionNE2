package fiscalizacionne

class UsuarioTagLib {
    static namespace = "usr"

    def usuarioService
    def comunaService

    def isAdmin = { attrs, body ->
        if(usuarioService.loggeUserIsAdmin())
            out << body()
    }

}
