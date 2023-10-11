package it.popso.popsogift.dto;

import lombok.Data;

@Data
public class SegnalazioneDTO {
    private Integer id;
    private String autore;
    private String descrizione;
    private BeneficiarioDTO beneficiario;
    private CampagnaDTO campagna;

}
