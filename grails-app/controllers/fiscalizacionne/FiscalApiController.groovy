package fiscalizacionne

import dto.response.MesaDTO
import dto.response.PartidoDTO
import dto.response.ResultadoMesaDTO
import grails.converters.JSON

class FiscalApiController {

    def gsonBuilder
    def usuarioService
    def fiscalService
    def partidoService

    static allowedMethods = [cargarMesa: "POST"]

    def getMesas(){
        Fiscal fiscal = usuarioService.getLoggedUser()
        List<MesaDTO> mesas = fiscalService.getMesas(fiscal)
        render mesas as JSON
    }

    def getPartidos(){
        List<PartidoDTO> partidos = partidoService.getPartidos()
        render partidos as JSON
    }

    def cargarMesas(){
        Fiscal fiscal = usuarioService.getLoggedUser()
        List<ResultadoMesaDTO> resultadosMesas = getRequestDTO()
        fiscalService.cargarResultados(fiscal, resultadosMesas)
    }

    def getResultados(){

    }

    private <Request> Request getRequestDTO(){
        def jsonObject = request.JSON
        def gson = gsonBuilder.create()

        Request req =  gson.fromJson(jsonObject.toString(), Request)
        return req
    }
}
