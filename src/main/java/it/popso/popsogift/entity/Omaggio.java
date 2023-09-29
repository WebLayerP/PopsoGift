package it.popso.popsogift.entity;

import it.popso.popsogift.dto.TagDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="OMAGGIO")
@Data
public class Omaggio {
    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="OMAGGIO_SEQ")
    @SequenceGenerator(name = "OMAGGIO_SEQ", sequenceName = "OMAGGIO_SEQ",allocationSize = 1)
    @Column(name="ID_OMAGGIO")
    private Integer idOmaggio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_CAMPAGNA")
    private Campagna campagna;
    @Column(name="NOME")
    private String nome;
    @Column(name="PREZZO")
    private String prezzo;
    @Column(name="DESCRIZIONE")
    private String descrizione;
    @Column(name="CODICE")
    private String codice;
//    @Column(name="TAG") //TODO Scommentare e collegare al momento opportuno
//    private TagDTO tag;
}
