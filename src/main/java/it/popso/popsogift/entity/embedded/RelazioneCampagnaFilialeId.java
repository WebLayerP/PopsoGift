package it.popso.popsogift.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class RelazioneCampagnaFilialeId {

    @Column(name = "ID_CAMPAGNA")
    private Integer idCampagna;

    @Column(name = "CODICE_FILIALE")
    private String codiceFiliale;
}
