package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiarioDetailDTO {
    private String ndg;
    private StatoBeneficiarioDTO stato;
    private String nome;
    private String cognome;
    private List<String> oggettiAssegnati;
    private List<String> storicoOggetti;
    private List<String> gruppi;
    private List<String> tags;
    private List<String> indirizzo;
}
