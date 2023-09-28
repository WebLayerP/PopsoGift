package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Entity
@Table(name="Campagna")
@Data
public class Campagna {
    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="CAMPAGNA_SEQ")
    @SequenceGenerator(name = "CAMPAGNA_SEQ", sequenceName = "CAMPAGNA_SEQ",allocationSize = 1)
    @Column(name="IDCAMPAGNA")
    private Integer idCampagna;
    @Column(name="TITOLOCAMPAGNA")
    private String titoloCampagna;
    @ManyToOne
    @JoinColumn(name="TIPOLOGIA")
    private Tipologia tipologia;
    @Column(name="DATAINIZIOMODIFICHE")
    private Date dataInizioModifiche;
    @Column(name="DATAFINEMODIFICHE")
    private Date dataFineModifiche;
    @OneToMany(mappedBy="campagna",fetch = FetchType.LAZY)
    private List<Omaggio> listaOmaggi;
    @OneToMany(mappedBy="campagna",fetch = FetchType.LAZY)
    private List<Filiale> listaFiliali;
    @ManyToOne
    @JoinColumn(name="STATO")
    private Stato stato;

}
