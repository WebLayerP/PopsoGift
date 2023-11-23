package it.popso.popsogift.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TagListDTO implements Serializable {
    private Integer numeroElementiTotali;
    private PaginazioneDTO paginazione;
    private List<TagOutputDTO> results;
}
