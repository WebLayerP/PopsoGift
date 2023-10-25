package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;
@Data
public class TagDTO {

    private String nomeTag;
    private Integer numeroBeneficiari;
    private Integer numeroOggetti;
    private Date dataInserimento;
    private String creatoDa;
    private Date dataAggiornamento;

}
