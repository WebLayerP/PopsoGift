package it.popso.popsogift.entity.embedded;

import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Filiale;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "REL_CAMPAGNA_FILIALE")
@Data
public class RelCampagnaFiliale {

    @EmbeddedId
    private RelazioneCampagnaFilialeId id;

    @ManyToOne
    @MapsId("campagnaId")
    @JoinColumn(name = "ID_CAMPAGNA")
    private Campagna campagna;

    @ManyToOne
    @MapsId("filialeId")
    @JoinColumn(name = "CODICE_FILIALE")
    private Filiale filiale;

    @Column(name = "CONFERMATO", nullable = true)
    private Integer confermato;
}
