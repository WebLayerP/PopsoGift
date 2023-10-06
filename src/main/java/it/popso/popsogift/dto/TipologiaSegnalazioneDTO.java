package it.popso.popsogift.dto;

import java.util.Objects;

public enum TipologiaSegnalazioneDTO {
    TIPOLOGIA1(1),
    TIPOLOGIA2(2),
    TIPOLOGIA3(3);

    private Integer idTipologiaSegnalazione;

    TipologiaSegnalazioneDTO(Integer idTipologiaSegnalazione) {
        this.idTipologiaSegnalazione = idTipologiaSegnalazione;
    }

    public Integer getIdTipologiaSegnalazione() {
        return idTipologiaSegnalazione;
    }


    public static TipologiaSegnalazioneDTO fromIdTipologiaSegnalazione(Integer idTipologiaSegnalazione) {
        for (TipologiaSegnalazioneDTO tipologiaSegnalazioneDTO : TipologiaSegnalazioneDTO.values()) {
            if (Objects.equals(tipologiaSegnalazioneDTO.getIdTipologiaSegnalazione(), idTipologiaSegnalazione)) {
                return tipologiaSegnalazioneDTO;
            }
        }
        throw new IllegalArgumentException("Tipologia segnalazione non valida: " + idTipologiaSegnalazione);
    }
}
