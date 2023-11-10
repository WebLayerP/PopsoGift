package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO {

    private String nomeTag;
    private Integer numeroBeneficiari;
    private Integer numeroOggetti;
    private Date dataInserimento;
    private String creatoDa;
    private Date dataAggiornamento;

}
