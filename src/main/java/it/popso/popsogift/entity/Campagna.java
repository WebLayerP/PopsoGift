//package it.popso.popsogift.entity;
//
//import it.popso.popsogift.dto.FilialeDTO;
//import it.popso.popsogift.dto.OmaggioDTO;
//import it.popso.popsogift.dto.StatoDTO;
//import it.popso.popsogift.dto.TipologiaDTO;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Data;
//
//import java.util.Date;
//import java.util.List;
//@Entity
//@Data
//public class Campagna {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer idCampagna;  //TODO: controllare se serve
//    private String titoloCampagna;
//    private TipologiaDTO tipologia;
//    private Date dataInizioModifiche;
//    private Date dataFineModifiche;
//    private List<OmaggioDTO> listaOmaggi;
//    private List<FilialeDTO> listaFiliali;
//    private StatoDTO stato;
//}
