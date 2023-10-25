package it.popso.popsogift.dto;

import java.io.Serializable;
import java.util.Objects;

public enum StatoDTO implements Serializable {
    IN_CORSO(1),
    BOZZA(2),
    CHIUSA(3);

    private Integer idStato;

    StatoDTO(Integer idStato) {
        this.idStato = idStato;
    }

    public Integer getIdStato() {
        return idStato;
    }


    public static StatoDTO fromIdStato(Integer idStato) {
        for (StatoDTO statoDTO : StatoDTO.values()) {
            if (Objects.equals(statoDTO.getIdStato(), idStato)) {
                return statoDTO;
            }
        }
        throw new IllegalArgumentException("Stato non valido: " + idStato);
    }
}
