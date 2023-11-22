package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OmaggioOverview {

    private int idOmaggio;
    private String codiceOmaggio;
    private String descrizioneBreve;
    private Double prezzo;
    private String tipologia;
    private String fornitore;
    private List<String> tag;
}
