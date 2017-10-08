package fiscalizacionne

import command.MesaEscuelaCommand
import dto.response.MesaDTO

class MesaService {

    def crearMesa(Mesa mesa){
        mesa.save(failOnError: true)
        Urna urna = new Urna()
        urna.mesa = mesa
        urna.save(failOnError:true)
        Partido.all?.forEach{ partido ->
            Boleta boleta = new Boleta()
            boleta.partido = partido
            urna.addToBoletas(boleta)
            boleta.save(failOnError:true)
            urna.save(failOnError:true)
        }
        urna.save(failonerror: true)
    }

    def borrarMesa(Mesa mesa){
        def urna = Urna.findByMesa(mesa)
        urna.boletas?.each {boleta ->
            boleta.delete(flush:true)
        }
        urna.delete(flush:true)
        mesa.delete(flush:true)
    }

    MesaEscuelaCommand get(Long id){
        Mesa mesa = Mesa.get(id)
        MesaEscuelaCommand mesaCommand = new MesaEscuelaCommand()
        mesaCommand.id = mesa.id
        mesaCommand.versionValue = mesa.version
        mesaCommand.numero = mesa.numero
        return mesaCommand
    }

    List<MesaEscuelaCommand> get(List<Long> ids){
        List<MesaEscuelaCommand> mesas = []
        ids.each {id -> mesas.add(get(id))}
        return mesas
    }

    Long save(MesaEscuelaCommand mesaCommand){
        Mesa mesa = new Mesa()
        mesa.id = mesaCommand.id
        mesa.version = mesaCommand.versionValue
        mesa.numero = mesaCommand.numero
        mesa.escuela = mesaCommand.escuela
        crearMesa(mesa)
        return mesa.id
    }

    List<Long> save(List<MesaEscuelaCommand> mesas){
        List<Long> mesasIds = []
        mesas.each {mesa ->
            mesasIds << save(mesa)
        }
        return mesasIds
    }

    MesaDTO toResponseDTO(Mesa mesa){
        MesaDTO mesaDTO = new MesaDTO()
        mesaDTO.mesa = mesa.numero
        mesaDTO.escuela = mesa.escuela.numero
        return mesaDTO
    }

}
