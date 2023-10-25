package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name ="OGGETTO")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Oggetto {
    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="OGGETTO_SEQ")
    @SequenceGenerator(name = "OGGETTO_SEQ", sequenceName = "OGGETTO_SEQ",allocationSize = 1)
    @Column(name="ID_OGGETTO")
    private Integer idOggetto;
    @Column(name="NOME")
    private String nome;
    @Column(name="PREZZO")
    private String prezzo;
    @Column(name="DESCRIZIONE")
    private String descrizione;
    @Column(name="CODICE")
    private String codice;
    @ManyToOne
    @JoinColumn(name="CATEGORIA")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name="FORNITORE")
    private Fornitore fornitore;
    @Column(name="DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;
    @Column(name="DATA_INSERIMENTO")
    private Date dataInserimento;
    @ManyToOne
    @JoinColumn(name="TIPOLOGIA")
    private TipologiaOggetto tipologiaOggetto;

}
