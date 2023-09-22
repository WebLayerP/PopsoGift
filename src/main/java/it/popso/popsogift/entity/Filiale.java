package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Filiale")
@Data
public class Filiale {
    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="FILIALE_SEQ")
    @SequenceGenerator(name = "FILIALE_SEQ", sequenceName = "FILIALE_SEQ",allocationSize = 1)
    private String codiceFiliale;

    @ManyToOne
    @JoinColumn(name="idCampagna")
    private Campagna campagna;
}
