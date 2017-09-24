package fiscalizacionne

class Escuela {

    Long numero
    Fiscal fiscal

    static belongsTo = [comuna: Comuna]
    static hasMany = [mesas: Mesa]

    static constraints = {
        fiscal nullable: true, blank:true
    }
}
