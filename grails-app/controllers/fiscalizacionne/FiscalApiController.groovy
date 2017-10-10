package fiscalizacionne

import common.ServiceException
import dto.response.MesaDTO
import dto.response.PartidoDTO
import dto.response.ResultadoDTO
import dto.response.ResultadoMesaDTO
import grails.converters.JSON

class FiscalApiController {

    def gsonBuilder
    def usuarioService
    def fiscalService
    def partidoService
    def resultadoService

    static allowedMethods = [cargarMesas: "POST"]

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
        try{
            List<Long> mesasActualizadas = fiscalService.cargarResultados(fiscal, resultadosMesas)
            render mesasActualizadas as JSON
        }catch (ServiceException se){
            response.status = 403
            render se.message
        }
        catch (Exception e){
            log.error(e.message)
            response.status = 500
            render "Error"
        }
    }

    def getResultados(){
        List<ResultadoDTO> resultados = resultadoService.getResultados()
        render resultados as JSON
    }

    private <Request> Request getRequestDTO(){
        def jsonObject = request.JSON
        def gson = gsonBuilder.create()

        Request req =  gson.fromJson(jsonObject.toString(), Request)
        return req
    }
}
