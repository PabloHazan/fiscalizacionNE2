package fiscalizacionne

class Urna {

    Mesa mesa

    static hasMany = [boletas: Boleta]

    static constraints = {
    }
}
