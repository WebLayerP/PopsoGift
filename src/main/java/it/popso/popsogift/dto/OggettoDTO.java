package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OggettoDTO implements Serializable {

    private Integer idOggetto;
    private String nome;
    private Double prezzo;
    private String descrizione;
    private Integer numeroColli;
    private String codice;
    private String categoria;
    private FornitoreDTO fornitore;
    private TipologiaOggettoDTO tipologia;
    private Date dataAggiornamento;
    private Date dataInserimento;
    private transient List<TagDTO> tag;
}
