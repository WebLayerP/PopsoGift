package it.popso.popsogift.dto;

import it.popso.popsogift.entity.TagEntity;
import lombok.Data;

@Data
public class OmaggioDTO {
    private String nome;
    private String prezzo;
    private String descrizione;
    private String codice;
    private TagEntity tag;
}
