package fiscalizacionne

import dto.response.ResultadoDTO

class ResultadoService {

    List<ResultadoDTO> getResultados() {
        List<ResultadoDTO> resultados = Partido.all.inject([] as List<ResultadoDTO>) {
            List<ResultadoDTO> r, Partido partido ->
            r << new ResultadoDTO(['partido': partido.nombre, 'color': partido.color, 'computa': partido.computa])
        }
        Long votosTotalesDiputados = 0
        Long votosTotalesLegisladores = 0
        Boleta.all.each { boleta ->
            ResultadoDTO resultado = resultados.find {r -> r.partido == boleta.partido.nombre}
            resultado.votosDiputados += boleta.diputados ?: 0L
            votosTotalesDiputados += boleta.diputados ?: 0L
            resultado.votosLegisladores += boleta.legisladores ?: 0L
            votosTotalesLegisladores += boleta.legisladores ?: 0L
        }
        resultados.each {resultado->
            resultado.porcentajeDiputados = resultado.votosDiputados * 100 / votosTotalesDiputados
            resultado.porcentajeLegisladores = resultado.votosLegisladores * 100 / votosTotalesLegisladores
        }
        return resultados
    }
}
