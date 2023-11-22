package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OmaggiListDTO {

    private Integer numeroElementiTotali;
    private PaginazioneDTO paginazione;
    private List<OmaggioOverview> results;
}
