package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="OGGETTO")
@Data
public class Oggetto {
    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="OGGETTO_SEQ")
    @SequenceGenerator(name = "OGGETTO_SEQ", sequenceName = "OGGETTO_SEQ",allocationSize = 1)
    @Column(name="ID_OGGETTO")
    private Integer idOggetto;

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

}
