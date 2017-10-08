package fiscalizacionne

class FuerzaPoliticaService {

    def usuarioService
    def comunaService

    List<Long> save(List<FuerzaPolitica> fuerzasPoliticas) {
        List<Long> fuerzasPoliticasIds = []
        fuerzasPoliticas.each {fuerzaPolitica ->
            fuerzasPoliticasIds << save(fuerzaPolitica)
        }
        return fuerzasPoliticasIds
    }

    Long save(FuerzaPolitica fuerzaPolitica){
        if (!fuerzaPolitica.color)
            fuerzaPolitica.color = 0
        fuerzaPolitica.save(flush:true, failOnError:true)
        return fuerzaPolitica.id
    }

    List<FuerzaPolitica> getAllByLoggedUser(){
        if (usuarioService.loggeUserIsAdmin())
            return FuerzaPolitica.all.toList()
        Comuna comuna = comunaService.getByAdministrador(usuarioService.getLoggedUser())
        return comuna.fuerzasPoliticas.toList()
    }
}
