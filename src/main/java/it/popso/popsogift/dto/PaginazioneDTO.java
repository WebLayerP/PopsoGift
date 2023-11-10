package it.popso.popsogift.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaginazioneDTO implements Serializable {

    private Integer numeroPagina;
    private Integer numeroElementiPerPagina;
    private Integer numeroPagine;
}
