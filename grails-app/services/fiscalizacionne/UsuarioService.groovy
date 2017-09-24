package fiscalizacionne

class UsuarioService {

    def springSecurityService

    Boolean isAdminOrAdminComunaValido(Long usuarioId){
        Fiscal usuarioLogeado = springSecurityService.currentUser
        Boolean isAdmin = usuarioLogeado.authorities.any {role -> role.authority.equals("ROLE_ADMIN")}
        return !(isAdmin || usuarioId == usuarioLogeado.id)
    }

}
