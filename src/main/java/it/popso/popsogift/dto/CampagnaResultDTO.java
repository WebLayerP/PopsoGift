package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CampagnaResultDTO {

    private Integer idCampagna;
    private String status;
    private String nome;
    private Date dataFineSegnalazioni;
    private Date dataFineCampagna;
    private Integer totOmaggi;
    private Long costo;
    private Integer filialeApprovato;
    private Integer filialeInAttesa;
    private String tipologia;
    private Date dataInizioModifiche;
    private Date dataFineModifiche;
    private List<OggettoDTO> listaOmaggi;
    private List<FilialeDTO> listaFiliali;
    private Date dataAggiornamento;
    private Date dataInserimento;
    private String matricola;
    private Integer numeroSegnalazioni;

    public CampagnaResultDTO() {
        //SONAR
    }
}
