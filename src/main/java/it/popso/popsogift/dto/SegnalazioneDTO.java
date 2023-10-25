package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SegnalazioneDTO {
    private Integer id;
    private String autore;
    private String descrizione;
    private BeneficiarioDTO beneficiario;
    private CampagnaDTO campagna;
    private Date dataAggiornamento;
    private Date dataInserimento;

}
