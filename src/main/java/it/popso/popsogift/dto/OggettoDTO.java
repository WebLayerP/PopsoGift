package it.popso.popsogift.dto;

import lombok.Data;

@Data
public class OggettoDTO {

    private Integer idOggetto;
    private String nome;
    private Long prezzo;
    private String descrizione;
    private String codice;
    private CategoriaDTO categoria;
    private FornitoreDTO fornitore;
}
