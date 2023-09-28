package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Filiale")
@Data
public class Filiale {
    @Id
    @Column(name="CODICEFILIALE")
    private String codiceFiliale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="IDCAMPAGNA")
    private Campagna campagna;
}
