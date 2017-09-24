package fiscalizacionne

/**
 * Created by pablo on 12/09/17.
 */
enum TipoFiscalEnum {
    GENERAL("Fiscal general", "ROLE_FISCAL_GENERAL"),
    MESA("Fiscal de mesa", "ROLE_FISCAL_MESA")

    String nombre
    String authority

    TipoFiscalEnum(String nombre, String authority) {
        this.nombre = nombre
        this.authority = authority
    }

    static TipoFiscalEnum findByAuthority(String authority){
        return fiscalizacionne.TipoFiscalEnum.values().find {it.authority==authority}
    }

}