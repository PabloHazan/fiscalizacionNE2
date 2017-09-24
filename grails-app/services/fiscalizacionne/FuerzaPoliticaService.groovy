package fiscalizacionne

class FuerzaPoliticaService {

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
}
