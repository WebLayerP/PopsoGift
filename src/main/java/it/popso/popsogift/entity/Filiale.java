package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Filiale")
@Data
public class Filiale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codiceFiliale;

    @ManyToOne
    @JoinColumn(name="idCampagna")
    private Campagna campagna;
}
