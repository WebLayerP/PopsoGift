package it.popso.popsogift.dto;

import lombok.Data;

import java.util.List;

@Data
public class FornitoreListDTO {

    private Integer numeroElementiTotali;
    private PaginazioneDTO paginazione;
    private List<FornitoreDTO> results;
}
