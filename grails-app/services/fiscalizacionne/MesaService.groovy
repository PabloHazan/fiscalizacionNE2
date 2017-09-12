package fiscalizacionne

class MesaService {

    def crearMesa(Mesa mesa){
        println "guardar mesa"
        mesa.save(failOnError: true)
        println "mesa guardada"
        Urna urna = new Urna()
        urna.mesa = mesa
        urna.save(failOnError:true)
        Partido.all?.forEach{ partido ->
            println partido.id
            Boleta boleta = new Boleta()
            boleta.partido = partido
            println "partido asignado"
            urna.addToBoletas(boleta)
            println "urna tiene boleta"
            boleta.save(failOnError:true)
            println "boleta guardada"
            urna.save(failOnError:true)
            println "urna guardada"

        }
        urna.save(failonerror: true)
    }

    def borrarMesa(Mesa mesa){
        def urna = Urna.findByMesa(mesa)
        println urna.id
        urna.boletas?.each {boleta ->
            boleta.delete(flush:true)
        }
        urna.delete(flush:true)
        mesa.delete(flush:true)
    }

}
