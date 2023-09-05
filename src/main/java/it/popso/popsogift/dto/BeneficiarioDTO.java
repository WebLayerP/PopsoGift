package it.popso.popsogift.dto;

import lombok.Data;

import java.util.List;

@Data
public class BeneficiarioDTO {
    private String ndg;
    private String cognome;
    private String nome;
    private OmaggioDTO oggettiAssegnati;
    private List<OmaggioDTO> storicoOggetti;
    private TagDTO tag;
    private GruppoDTO gruppo;
    private String indirizzo;

}
