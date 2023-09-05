package it.popso.popsogift.dto;

import it.popso.popsogift.entity.GruppoEntity;
import it.popso.popsogift.entity.TagEntity;
import lombok.Data;

import java.util.List;

@Data
public class BeneficiarioDTO {
    private String cognome;
    private String nome;
    private List<OmaggioDTO> oggettiAssegnati;
    private List<StoricoDTO> storicoOggetti;
    private TagEntity tag;
    private GruppoEntity gruppo;
    private String indirizzo;

}
