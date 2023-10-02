package it.popso.popsogift.dto;

import lombok.Data;

import java.util.List;

@Data
public class BeneficiarioDTO {

    private Integer idBeneficiario;
    private String ndg;
    private String cognome;
    private String nome;
    private OggettoDTO oggettiAssegnati;
    private List<OggettoDTO> storicoOggetti;
    private TagDTO tag;
    private GruppoDTO gruppo;
    private String indirizzo;

}
