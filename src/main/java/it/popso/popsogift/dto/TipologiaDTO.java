package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum TipologiaDTO implements Serializable {
    FISICA(1),
    DIGITALE(2),
    IBRIDA(3);


    private Integer idTipologia;

    TipologiaDTO(Integer idTipologia) {
        this.idTipologia = idTipologia;
    }
    @JsonValue
    public Integer getIdTipologia() {
        return idTipologia;
    }
}
