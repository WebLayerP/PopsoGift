package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="SEGNALAZIONE")
@Data
public class Segnalazione {

    @Id
    @Column(name="ID")
    private Integer idFornitore;
    @Column(name="AUTORE")
    private String autore;
    @Column(name="DESCRIZIONE")
    private String descrizione;
    @ManyToOne
    @JoinColumn(name="TIPOLOGIA_SEGNALAZIONE")
    private TipologiaSegnalazione tipologiaSegnalazione;
    @ManyToOne
    @JoinColumn(name="ID_CAMPAGNA")
    private Campagna campagna;
    @ManyToOne
    @JoinColumn(name="ID_BENEFICIARIO")
    private Beneficiario beneficiario;
}
