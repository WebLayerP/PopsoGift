package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="NOTIFICA")
@Data
public class Notifica {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="NOTIFICA_SEQ")
    @SequenceGenerator(name = "NOTIFICA_SEQ", sequenceName = "NOTIFICA_SEQ",allocationSize = 1)
    @Column(name="ID_NOTIFICA")
    private Integer idNotifica;

    @Column(name="TITOLO")
    private String titolo;
    @Column(name="DESCRIZIONE")
    private String descrizione;
    @Column(name="LETTA")
    private Boolean letta;
    @Column(name="SCADENZA")
    private Date scadenza;
    @Column(name="RUOLO")
    private String ruolo;
    @Column(name="DATA_INSERIMENTO")
    private Date dataInserimento;
    @ManyToOne
    @JoinColumn(name="ID_TIPOLOGIA")
    private TipologiaNotifica tipologiaNotifica;
    @ManyToOne
    @JoinColumn(name="ID_CAMPAGNA")
    private Campagna campagna;
    @ManyToOne
    @JoinColumn(name="CODICE_FILIALE")
    private Filiale filiale;


}
