package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="Omaggio")
@Data
public class Omaggio {
    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="OMAGGIO_SEQ")
    @SequenceGenerator(name = "OMAGGIO_SEQ", sequenceName = "OMAGGIO_SEQ",allocationSize = 1)
    private Integer idOmaggio;

    @ManyToOne
    @JoinColumn(name="idCampagna")
    private Campagna campagna;
}
