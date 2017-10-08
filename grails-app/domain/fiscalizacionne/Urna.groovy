package fiscalizacionne

class Urna {

    Mesa mesa
    Fiscal informante

    static hasMany = [boletas: Boleta]

    static constraints = {
        informante nullable: true
    }

    static mapping = {
        boletas cascade: 'all-delete-orphan'
    }
}
