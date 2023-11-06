package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GruppoDTO {
    private Integer idGruppo;
    private String nome;
    private String descrizione;
    private List<BeneficiarioDTO> listaBeneficiari;
    private Date dataAggiornamento;
    private Date dataInserimento;
}
