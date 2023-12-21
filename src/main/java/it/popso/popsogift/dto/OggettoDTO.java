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
    private Integer tipologia;
    private Date dataAggiornamento;
    private Date dataInserimento;
    private List<TagDTO> tag;
    private Boolean statoCancellazione;
    private Date dataCancellazione;
    private String idCancellazione;
}
