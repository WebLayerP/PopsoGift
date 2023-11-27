package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_BENEFICIARIO_OGGETTO_CAMPAGNA",
            joinColumns = @JoinColumn(name = "NDG"),
            inverseJoinColumns = @JoinColumn(name = "ID_OGGETTO"))
    private List<Oggetto> listaOggetti;

    @Column(name="PRIVACY")
    private Boolean privacy;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_BENEFICIARIO_TAG",
            joinColumns = @JoinColumn(name = "NDG"),
            inverseJoinColumns = @JoinColumn(name = "ID_TAG"))
    private List<Tag> tag;

}
