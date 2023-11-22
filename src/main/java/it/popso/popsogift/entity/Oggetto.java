package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name ="OGGETTO")
public class Oggetto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OGGETTO_SEQ")
    @SequenceGenerator(name = "OGGETTO_SEQ", sequenceName = "OGGETTO_SEQ", allocationSize = 1)
    @Column(name = "ID_OGGETTO")
    private Integer idOggetto;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "PREZZO")
    private Double prezzo;
    @Column(name = "DESCRIZIONE")
    private String descrizione;
    @Column(name = "CODICE")
    private String codice;
    @ManyToOne
    @JoinColumn(name = "CATEGORIA")
    private Categoria categoria;
    @Column(name = "NUMERO_COLLI")
    private Integer numeroColli;
    @ManyToOne
    @JoinColumn(name = "FORNITORE")
    private Fornitore fornitore;
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;
    @ManyToOne
    @JoinColumn(name = "TIPOLOGIA")
    private TipologiaOggetto tipologia;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_OGGETTO_TAG",
            joinColumns = @JoinColumn(name = "ID_OGGETTO"),
            inverseJoinColumns = @JoinColumn(name = "ID_TAG"))
    private List<Tag> tag;

}