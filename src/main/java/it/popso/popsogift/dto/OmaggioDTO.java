package it.popso.popsogift.dto;

import lombok.Data;

@Data
public class OmaggioDTO {

    private Integer idOmaggio;
    private String nome;
    private Long prezzo;
    private String descrizione;
    private String codice;
    private TagDTO tag;
}
