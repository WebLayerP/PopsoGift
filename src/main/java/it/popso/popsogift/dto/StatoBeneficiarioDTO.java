package it.popso.popsogift.dto;


import java.io.Serializable;
import java.util.Objects;

public enum StatoBeneficiarioDTO implements Serializable {

    OK(1),
    INATTESAPRIVACY(2),
    ESTINTO(3);

    private Integer idStato;

    StatoBeneficiarioDTO() {
        // Costruttore vuoto
    }

    StatoBeneficiarioDTO(Integer idStato) {
        this.idStato = idStato;
    }

    public Integer getIdStato() {
        return idStato;
    }


    public static StatoBeneficiarioDTO fromIdStato(Integer idStato) {
        for (StatoBeneficiarioDTO statoBeneficiarioDTO : StatoBeneficiarioDTO.values()) {
            if (Objects.equals(statoBeneficiarioDTO.getIdStato(), idStato)) {
                return statoBeneficiarioDTO;
            }
        }
        throw new IllegalArgumentException("Stato Beneficiario non valido: " + idStato);
    }
}
