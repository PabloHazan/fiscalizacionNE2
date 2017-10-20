package fiscalizacionne

class UsuarioService {

    def springSecurityService

    Boolean loggeUserIsAdmin(){
        Fiscal usuarioLogeado = springSecurityService.currentUser
        return isAdmin(usuarioLogeado)
    }

    Boolean isAdmin(Fiscal usuario){
        return usuario.authorities.any {role -> role.authority.equals("ROLE_ADMIN")}
    }

    Boolean isAdminOrAdminComunaValido(Long usuarioId){
        Fiscal usuarioLogeado = springSecurityService.currentUser
        Boolean isAdmin = usuarioLogeado.authorities.any {role -> role.authority.equals("ROLE_ADMIN")}
        return !(isAdmin || usuarioId == usuarioLogeado.id)
    }

    Boolean isFiscal(Fiscal usuario){
        return usuario.authorities.any {role ->
            role.authority.equals("ROLE_FISCAL_GENERAL") || role.authority.equals("ROLE_FISCAL_MESA")
        }
    }

    Boolean isFiscalMesa (Fiscal usuario){
        return usuario.authorities.any {role ->
            role.authority.equals("ROLE_FISCAL_MESA")
        }
    }

    Boolean isFiscalGeneral(Fiscal usuario){
        return usuario.authorities.any {role ->
            role.authority.equals("ROLE_FISCAL_GENERAL")
        }
    }

    Boolean isAdminComuna(Fiscal usuario){
        return usuario.authorities.any {role ->
            role.authority.equals("ROLE_ADMIN_COMUNA")
        }
    }

    Fiscal getLoggedUser(){
        return springSecurityService.currentUser
    }

}
