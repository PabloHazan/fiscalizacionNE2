package dto.response

/**
 * Created by pablo on 10/10/17.
 */
class ResultadoDTO {
    String partido
    Long partidoId
    Long partidoNumero
    Long color
    Long votosDiputados = 0L
    Float porcentajeDiputados = 0F
    Long votosLegisladores = 0L
    Float porcentajeLegisladores = 0F
    Boolean computa
}
