package it.popso.popsogift.dto;

import java.io.Serializable;
import java.util.Objects;

public enum TipologiaDTO implements Serializable {
    FISICA(1),
    DIGITALE(2),
    IBRIDA(3);


    private Integer idTipologia;

    TipologiaDTO(Integer idTipologia) {
        this.idTipologia = idTipologia;
    }

    public Integer getIdTipologia() {
        return idTipologia;
    }

    public static TipologiaDTO fromIdTipologia(Integer idTipologia) {
        for (TipologiaDTO tipologiaDTO : TipologiaDTO.values()) {
            if (Objects.equals(tipologiaDTO.getIdTipologia(), idTipologia)) {
                return tipologiaDTO;
            }
        }
        throw new IllegalArgumentException("Tipologia non valida: " + idTipologia);
    }
}
