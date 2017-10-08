package fiscalizacionne

class Escuela {

    Long numero
    Fiscal fiscal
    FuerzaPolitica fuerzaPolitica

    static belongsTo = [comuna: Comuna]
    static hasMany = [mesas: Mesa]

    static constraints = {
        fiscal nullable: true, blank:true
        fuerzaPolitica nullable: true, blank: true
    }
}
