package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SegnalazioneDTO {
    private Integer id;
    private String autore;
    private String descrizione;
    private BeneficiarioDTO beneficiario;
    private CampagnaDTO campagna;
    private Date dataAggiornamento;
    private Date dataInserimento;

}
