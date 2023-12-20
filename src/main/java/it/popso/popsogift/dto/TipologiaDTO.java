package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipologiaDTO {
    private Integer idTipologia;
    private String nomeTipologia;
}
