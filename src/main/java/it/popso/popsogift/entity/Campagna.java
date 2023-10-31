package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name="CAMPAGNA")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Campagna {
    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="CAMPAGNA_SEQ")
    @SequenceGenerator(name = "CAMPAGNA_SEQ", sequenceName = "CAMPAGNA_SEQ",allocationSize = 1)
    @Column(name="ID_CAMPAGNA")
    private Integer idCampagna;
    @Column(name="TITOLO_CAMPAGNA")
    private String titoloCampagna;
    @ManyToOne
    @JoinColumn(name="TIPOLOGIA")
    private Tipologia tipologia;
    @Column(name="DATA_FINE")
    private Date dataFine;
    @Column(name="DATA_INIZIO_MODIFICHE")
    private Date dataInizioModifiche;
    @Column(name="DATA_FINE_MODIFICHE")
    private Date dataFineModifiche;
    @Column(name="DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;
    @Column(name="DATA_INSERIMENTO")
    private Date dataInserimento;
    @Column(name="MATRICOLA")
    private String matricola;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_CAMPAGNA_OGGETTO",
            joinColumns = @JoinColumn(name = "ID_CAMPAGNA"),
            inverseJoinColumns = @JoinColumn(name = "ID_OGGETTO"))
    private List<Oggetto> listaOmaggi;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_CAMPAGNA_FILIALE",
            joinColumns = @JoinColumn(name = "ID_CAMPAGNA"),
            inverseJoinColumns = @JoinColumn(name = "CODICE_FILIALE"))
    private List<Filiale> listaFiliali;
    @ManyToOne
    @JoinColumn(name="STATO")
    private Stato stato;
    @OneToMany(mappedBy="campagna")
    private List<Segnalazione> segnalazione;

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public List<Segnalazione> getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(List<Segnalazione> segnalazione) {
        this.segnalazione = segnalazione;
    }

    public List<Oggetto> getListaOmaggi() {
        return listaOmaggi;
    }

    public void setListaOmaggi(List<Oggetto> listaOmaggi) {
        this.listaOmaggi = listaOmaggi;
    }

    public List<Filiale> getListaFiliali() {
        return listaFiliali;
    }

    public void setListaFiliali(List<Filiale> listaFiliali) {
        this.listaFiliali = listaFiliali;
    }

    public Integer getIdCampagna() {
        return idCampagna;
    }

    public void setIdCampagna(Integer idCampagna) {
        this.idCampagna = idCampagna;
    }

    public String getTitoloCampagna() {
        return titoloCampagna;
    }

    public void setTitoloCampagna(String titoloCampagna) {
        this.titoloCampagna = titoloCampagna;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public Date getDataInizioModifiche() {
        return dataInizioModifiche;
    }

    public void setDataInizioModifiche(Date dataInizioModifiche) {
        this.dataInizioModifiche = dataInizioModifiche;
    }

    public Date getDataFineModifiche() {
        return dataFineModifiche;
    }

    public void setDataFineModifiche(Date dataFineModifiche) {
        this.dataFineModifiche = dataFineModifiche;
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

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}
