package fiscalizacionne

class PartidoService {

    def crearPartido(Partido partido){
        partido.save(failOnError:true)
        def urnas = Urna.all
        urnas.each {urna ->
            def boleta = new Boleta()
            boleta.partido = partido
            urna.addToBoletas(boleta)
            urna.save(failOnError:true)
            boleta.save(failOnError:true)
        }
    }
}
