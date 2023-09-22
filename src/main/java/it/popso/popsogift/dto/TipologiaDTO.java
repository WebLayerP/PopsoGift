package it.popso.popsogift.dto;

import java.io.Serializable;

public enum TipologiaDTO implements Serializable {
    FISICA(1),
    DIGITALE(2),
    IBRIDA(3);

    TipologiaDTO() {
    }

    private Integer idTipologia;

    TipologiaDTO(Integer idTipologia) {
        this.idTipologia = idTipologia;
    }
    //@JsonValue
    public Integer getIdTipologia() {
        return idTipologia;
    }

//    @JsonCreator
//    public static TipologiaDTO fromNomeStato(String idTipologia) {
//        for (TipologiaDTO tipologiaDTO : TipologiaDTO.values()) {
//            if (tipologiaDTO.getidTipologia().equalsIgnoreCase(idTipologia)) {
//                return tipologiaDTO;
//            }
//        }
//        throw new IllegalArgumentException("TipologiaDTO non valida: " + idTipologia);
//    }
}
