package it.popso.popsogift.dto;

import lombok.Data;

@Data
public class OmaggioDTO {

    private Integer idOmaggio; //TODO controllare se serve
    private String nome;
    private String prezzo;
    private String descrizione;
    private String codice;
    private TagDTO tag;
}
