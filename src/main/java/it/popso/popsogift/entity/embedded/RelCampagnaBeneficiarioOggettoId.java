package it.popso.popsogift.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class RelCampagnaBeneficiarioOggettoId {

    @Column(name = "ID_CAMPAGNA")
    private Integer idCampagna;

    @Column(name = "NDG")
    private String ndg;

    @Column(name = "ID_OGGETTO")
    private Integer idOggetto;
}
