package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
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

}
