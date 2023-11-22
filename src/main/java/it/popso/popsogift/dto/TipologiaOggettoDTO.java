package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
public enum TipologiaOggettoDTO implements Serializable {

    FISICO(1),
    DIGITALE(2);


    private int idTipologiaOggetto;

    TipologiaOggettoDTO(int idTipologiaOggetto) {
        this.idTipologiaOggetto = idTipologiaOggetto;
    }
    @JsonValue
    public int getIdTipologiaOggetto() {
        return idTipologiaOggetto;
    }

}
