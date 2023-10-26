package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificaDTO {

    private Integer idNotifica;

    private String titolo;

    private String descrizione;

    private Boolean letta;

    private Date scadenza;

    private String ruolo;

    private Date dataInserimento;

    private String tipologiaNotifica;

    private CampagnaDTO campagna;

    private FilialeDTO filiale;

}
