package fiscalizacionne

class Comuna {

    Long numero
    Fiscal admin
    static hasMany = [
            escuelas: Escuela,
            fuerzasPoliticas: FuerzaPolitica
    ]

    static constraints = {
        numero nullable: false, unique: true
        admin nullable: false, blank:false
    }
}
