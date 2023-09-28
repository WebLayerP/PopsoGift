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
    @Column(name="IDOMAGGIO")
    private Integer idOmaggio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="IDCAMPAGNA")
    private Campagna campagna;
}
