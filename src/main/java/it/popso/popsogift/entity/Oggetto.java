package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="OGGETTO")
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
    private Double prezzo;
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

    public Integer getIdOggetto() {
        return idOggetto;
    }

    public void setIdOggetto(Integer idOggetto) {
        this.idOggetto = idOggetto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public Date getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(Date dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public TipologiaOggetto getTipologiaOggetto() {
        return tipologiaOggetto;
    }

    public void setTipologiaOggetto(TipologiaOggetto tipologiaOggetto) {
        this.tipologiaOggetto = tipologiaOggetto;
    }

}
