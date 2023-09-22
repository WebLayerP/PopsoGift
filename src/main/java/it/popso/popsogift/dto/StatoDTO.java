package it.popso.popsogift.dto;

import java.io.Serializable;

public enum StatoDTO implements Serializable {
    APPROVATO(1),
    INATTESA(2),
    BOZZA(3);

    private Integer idStato;

    StatoDTO(Integer idStato) {
        this.idStato = idStato;
    }

    StatoDTO() {
    }

    //@JsonValue
    public Integer getIdStato() {
        return idStato;
    }

//    @JsonCreator
//    public static StatoDTO fromNomeStato(String nomeStato) {
//        for (StatoDTO statoDTO : StatoDTO.values()) {
//            if (statoDTO.getNomeStato().equalsIgnoreCase(nomeStato)) {
//                return statoDTO;
//            }
//        }
//        throw new IllegalArgumentException("StatoDTO non valido: " + nomeStato);
//    }
}
