package fiscalizacionne

class Mesa {

    Integer numero
    Fiscal  fiscal

    static belongsTo = [escuela: Escuela]

    static constraints = {
        fiscal nullable: true, blank:true
    }
}
