package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CampagnaDTO {

    private Integer idCampagna;  //TODO: controllare se serve
    private String titoloCampagna;
    private TipologiaDTO tipologia;
    private Date dataInizioModifiche;
    private Date dataFineModifiche;
    private List<OmaggioDTO> listaOmaggi;
    private List<FilialeDTO> listaFiliali;
    private StatoDTO stato;

    public CampagnaDTO() {
    }

    public CampagnaDTO(Integer idCampagna, String titoloCampagna, TipologiaDTO tipologia, Date dataInizioModifiche, Date dataFineModifiche, List<OmaggioDTO> listaOmaggi, List<FilialeDTO> listaFiliali, StatoDTO stato) {

    }
}
