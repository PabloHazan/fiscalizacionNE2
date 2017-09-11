package fiscalizacionne

class Boleta {

    Long diputados
    Long legisladores
    Partido partido

    static belongsTo = [urna: Urna]

    static constraints = {
    }
}
