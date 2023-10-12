package it.popso.popsogift.dto;

import it.popso.popsogift.entity.Filiale;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BeneficiarioDTO {

    private String ndg;
    private Date dataInserimento;
    private String note;
    private StatoBeneficiarioDTO statoBeneficiario;
    private Filiale filiale;
    private Date dataEstinzione;
    private String motivoEstinzione;
    private List<GruppoDTO> listaGruppi;

}
