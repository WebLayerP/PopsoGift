package it.popso.popsogift.entity;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.dto.OmaggioDTO;
import it.popso.popsogift.dto.StatoDTO;
import it.popso.popsogift.dto.TipologiaDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Entity
@Table(name="Campagna")
@Data
public class Campagna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCampagna;  //TODO: controllare se serve
    private String titoloCampagna;
    private TipologiaDTO tipologia;
    private Date dataInizioModifiche;
    private Date dataFineModifiche;
    @OneToMany(mappedBy="campagna")
    private List<Omaggio> listaOmaggi;
    @OneToMany(mappedBy="campagna")
    private List<Filiale> listaFiliali;
    private StatoDTO stato;
}
