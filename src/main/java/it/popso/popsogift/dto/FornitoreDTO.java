package it.popso.popsogift.dto;

import lombok.Data;

import java.io.Serializable;

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
}
