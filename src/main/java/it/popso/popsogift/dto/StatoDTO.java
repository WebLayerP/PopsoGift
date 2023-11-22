package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum StatoDTO implements Serializable {
    IN_CORSO(1),
    BOZZA(2),
    CHIUSA(3);

    private Integer idStato;

    StatoDTO(Integer idStato) {
        this.idStato = idStato;
    }
    @JsonValue
    public Integer getIdStato() {
        return idStato;
    }
}
