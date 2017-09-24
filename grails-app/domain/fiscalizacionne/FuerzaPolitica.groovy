package fiscalizacionne

class FuerzaPolitica {

    String nombre
    Long color
    static relatesToMany = [comunas: Comuna]

    static constraints = {
        nombre nullable:false, blank: false, unique: true
    }
}
