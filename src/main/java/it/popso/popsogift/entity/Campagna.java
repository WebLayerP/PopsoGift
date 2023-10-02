package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Entity
@Table(name="CAMPAGNA")
@Data
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
    @Column(name="DATA_INIZIO_MODIFICHE")
    private Date dataInizioModifiche;
    @Column(name="DATA_FINE_MODIFICHE")
    private Date dataFineModifiche;
    @OneToMany(mappedBy="campagna",fetch = FetchType.LAZY)
    private List<Oggetto> listaOmaggi;
    @OneToMany(mappedBy="campagna",fetch = FetchType.LAZY)
    private List<Filiale> listaFiliali;
    @ManyToOne
    @JoinColumn(name="STATO")
    private Stato stato;

}
