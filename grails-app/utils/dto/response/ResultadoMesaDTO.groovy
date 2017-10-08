package dto.response

/**
 * Created by pablo on 07/10/17.
 */
class ResultadoMesaDTO {
    Long mesa
    List<ResultadoPartidoMesaDTO> resultados

    public ResultadoMesaDTO(){
        resultados = []
    }
}
/*
[
  {
    "mesa": <numeroMesa>,
    "resultados": [
      {
        "partido": <partidoId>,
        "senadores": <votosSenadores>,
        "legisladores": <votosLegisladores>
      }
    ]
  }
]

 */
