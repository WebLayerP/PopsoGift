package it.popso.popsogift.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class FornitoreDTO implements Serializable {

    private Integer idFornitore;
    private String ragioneSociale;
    private String email;
    private String indirizzo;
    private String stato;
    private String cap;
    private String telefono;
    private String citta;
    private String provincia;
    private String partitaIva;
    private Date dataAggiornamento;
    private Date dataInserimento;
    private Boolean statoCancellazione;
    private Date dataCancellazione;
    private String idCancellazione;
    private List<OggettoDTO> listaOggetti;
}

