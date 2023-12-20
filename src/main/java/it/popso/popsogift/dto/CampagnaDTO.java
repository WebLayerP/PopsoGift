package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CampagnaDTO {

    private Integer idCampagna;
    private String titoloCampagna;
    private Integer tipologia;
    private Date dataInizioModifiche;
    private Date dataFineModifiche;
    private List<OggettoDTO> listaOmaggi;
    private List<FilialeDTO> listaFiliali;
    private Date dataAggiornamento;
    private Date dataInserimento;
    private String matricola;
    private Integer stato;
    private Integer numeroSegnalazioni;

    public CampagnaDTO() {
        //SONAR
    }
}
