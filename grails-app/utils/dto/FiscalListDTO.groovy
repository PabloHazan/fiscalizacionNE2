package dto

import fiscalizacionne.TipoFiscalEnum

/**
 * Created by pablo on 17/09/17.
 */
class FiscalListDTO {
    Long id
    Long versionValue
    String username
    String mail
    TipoFiscalEnum tipo


    public FiscalListDTO(){

    }

    public FiscalListDTO(Long id, Long versionValue, String username, String mail, String authority){
        this.id = id
        this.versionValue = versionValue
        this.username = username
        this.mail = mail
        this.tipo = TipoFiscalEnum.findByAuthority(authority)
    }
}
