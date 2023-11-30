package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private Boolean privacy;
    private String nome;
    private String cognome;
    private Boolean statoCancellazione;
    private Date dataCancellazione;
    private String idCancellazione;
    private String matricolaProponente;

}
