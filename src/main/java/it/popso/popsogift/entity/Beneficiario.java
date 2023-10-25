package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="BENEFICIARIO")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="ndg")
public class Beneficiario {
    @Id
    @Column(name="NDG")
    private String ndg;
    @Column(name="DATA_INSERIMENTO")
    private Date dataInserimento;
    @Column(name="NOTE")
    private String note;
    @ManyToOne
    @JoinColumn(name="STATO")
    private StatoBeneficiario statoBeneficiario;
    @ManyToOne
    @JoinColumn(name="CODICE_FILIALE")
    private Filiale filiale;
    @Column(name="DATA_ESTINZIONE")
    private Date dataEstinzione;
    @Column(name="MOTIVO_ESTINZIONE")
    private String motivoEstinzione;

    @Column(name="DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_BENEFICIARIO_GRUPPO",
            joinColumns = @JoinColumn(name = "NDG"),
            inverseJoinColumns = @JoinColumn(name = "GRUPPO"))
    private List<Gruppo> listaGruppi;

    public String getNdg() {
        return ndg;
    }

    public void setNdg(String ndg) {
        this.ndg = ndg;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public Date getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(Date dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StatoBeneficiario getStatoBeneficiario() {
        return statoBeneficiario;
    }

    public void setStatoBeneficiario(StatoBeneficiario statoBeneficiario) {
        this.statoBeneficiario = statoBeneficiario;
    }

    public Filiale getFiliale() {
        return filiale;
    }

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    }

    public Date getDataEstinzione() {
        return dataEstinzione;
    }

    public void setDataEstinzione(Date dataEstinzione) {
        this.dataEstinzione = dataEstinzione;
    }

    public String getMotivoEstinzione() {
        return motivoEstinzione;
    }

    public void setMotivoEstinzione(String motivoEstinzione) {
        this.motivoEstinzione = motivoEstinzione;
    }

    public List<Gruppo> getListaGruppi() {
        return listaGruppi;
    }

    public void setListaGruppi(List<Gruppo> listaGruppi) {
        this.listaGruppi = listaGruppi;
    }
}
