package fiscalizacionne

class PartidoService {

    def crearPartido(Partido partido){
        partido.save()
        def urnas = Urna.all
        urnas.each {urna ->
            def boleta = new Boleta()
            boleta.partido = partido
            boleta.save()
            urna.addToBoletas(boleta)
            urna.save()
        }
    }
}
