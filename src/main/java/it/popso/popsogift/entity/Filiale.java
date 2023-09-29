package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="FILIALE")
@Data
public class Filiale {
    @Id
    @Column(name="CODICE_FILIALE")
    private String codiceFiliale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_CAMPAGNA")
    private Campagna campagna;

}
