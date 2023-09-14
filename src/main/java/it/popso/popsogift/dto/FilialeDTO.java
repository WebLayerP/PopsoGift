package it.popso.popsogift.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilialeDTO {

    private String codiceFiliale;
    private String nomeFiliale;
    private String indirizzo;
    private List<BeneficiarioDTO> listaBeneficiari;
}
