package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GruppoDTO {
    private Integer idGruppo;
    private String nome;
    private String descrizione;
    private List<BeneficiarioDTO> listaBeneficiari;
    private Date dataAggiornamento;
    private Date dataInserimento;
}
