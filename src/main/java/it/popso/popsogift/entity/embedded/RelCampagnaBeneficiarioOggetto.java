package it.popso.popsogift.entity.embedded;

import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Filiale;
import it.popso.popsogift.entity.Oggetto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "REL_BENEFICIARIO_OGGETTO_CAMPAGNA")
@Data
public class RelCampagnaBeneficiarioOggetto {

    @EmbeddedId
    private RelCampagnaBeneficiarioOggettoId id;

    @ManyToOne
    @MapsId("campagnaId")
    @JoinColumn(name = "ID_CAMPAGNA")
    private Campagna campagna;

    @ManyToOne
    @MapsId("beneficiarioId")
    @JoinColumn(name = "NDG")
    private Filiale filiale;

    @ManyToOne
    @MapsId("oggettoId")
    @JoinColumn(name = "ID_OGGETTO")
    private Oggetto oggetto;

    @Column(name = "NUMERO_ITEM")
    private Integer numeroItem;

}
