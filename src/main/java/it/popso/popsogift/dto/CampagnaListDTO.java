package it.popso.popsogift.dto;

import lombok.Data;

import java.util.List;

@Data
public class CampagnaListDTO {

    private Integer numeroElementiTotali;
    private PaginazioneDTO paginazione;
    private List<CampagnaResultDTO> results;
}
