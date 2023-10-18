package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilialeDTO {

    private String codiceFiliale;

    private String nomeFiliale;

    private String indirizzo;

    private Integer numeroBeneficiari;
}
