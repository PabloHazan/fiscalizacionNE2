package fiscalizacionne

import dto.response.PartidoDTO

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

    List<PartidoDTO> getPartidos(){
        List<PartidoDTO> partidos = []
        Partido.all.each {partido ->
            partidos << toResponseDTO(partido)
        }
        return partidos
    }

    PartidoDTO toResponseDTO(Partido partido){
        PartidoDTO partidoDTO = new PartidoDTO()
        partidoDTO.id = partido.id
        partidoDTO.nombre = partido.nombre
        //TODO: agregar color los partidos
        partidoDTO.color = 0xFFFF//partido.color
        partidoDTO.lista = partido.numero
        return partidoDTO
    }
}
