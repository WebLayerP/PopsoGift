package it.popso.popsogift.dto;

import java.io.Serializable;
import java.util.Objects;

public enum StatoDTO implements Serializable {
    APPROVATO(1),
    INATTESA(2),
    BOZZA(3);

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
