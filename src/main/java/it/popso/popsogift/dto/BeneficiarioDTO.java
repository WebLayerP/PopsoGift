package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.popso.popsogift.entity.Filiale;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiarioDTO {

    private String ndg;
    private Date dataInserimento;
    private Date dataAggiornamento;
    private String note;
    private StatoBeneficiarioDTO statoBeneficiario;
    private String motivoEstinzione;
    private List<GruppoDTO> listaGruppi;
    private List<OggettoDTO> listaOggetti;
    private List<TagDTO> tag;
    private Boolean privacy;
    private Boolean statoCancellazione;
    private Date dataCancellazione;
    private String idCancellazione;
    private FilialeDTO filiale;
    private String matricolaProponente;

}
