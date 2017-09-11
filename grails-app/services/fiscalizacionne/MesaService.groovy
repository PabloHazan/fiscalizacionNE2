package fiscalizacionne

class MesaService {

    def crearMesa(Mesa mesa){
        println "guardar mesa"
        mesa.save(failonerror: true)
        println "mesa guardada"
        Urna urna = new Urna()
        urna.mesa = mesa
        Partido.all?.forEach{ partido ->
            Boleta boleta = new Boleta()
            boleta.partido = partido
            boleta.save()
            urna.addToBoletas(boleta)
        }
        urna.save(failonerror: true)
    }

    def borrarMesa(Mesa mesa){
        def urna = Urna.findByMesa(mesa)
        println urna.id
        urna.boletas*.delete()
        urna.delete()
        mesa.delete()
    }

}
