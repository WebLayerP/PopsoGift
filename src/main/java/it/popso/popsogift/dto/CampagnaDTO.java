package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CampagnaDTO {

    private Integer idCampagna;
    private String titoloCampagna;
    private TipologiaDTO tipologia;
    private Date dataInizioModifiche;
    private Date dataFineModifiche;
    private List<OggettoDTO> listaOmaggi;
    private List<FilialeDTO> listaFiliali;
    private Date dataAggiornamento;
    private Date dataInserimento;
    private String matricola;
    private StatoDTO stato;

    public CampagnaDTO() {
    }
}
