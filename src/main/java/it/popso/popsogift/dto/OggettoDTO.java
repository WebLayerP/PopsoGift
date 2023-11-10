package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OggettoDTO implements Serializable {

    private Integer idOggetto;
    private String nome;
    private Long prezzo;
    private String descrizione;
    private String codice;
    private CategoriaDTO categoria;
    private FornitoreDTO fornitore;
    private Date dataAggiornamento;
    private Date dataInserimento;
}
