package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="Omaggio")
@Data
public class Omaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOmaggio; //TODO controllare se serve

    @ManyToOne
    @JoinColumn(name="idCampagna")
    private Campagna campagna;
}
