package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PanoramicaOverview {

    private int numeroBeneficiari;
    private Date dataAggiornamento;
    private int numeroPrivacyKO;
    private int numeroMailKO;
    private int numeroIndirizziKO;
}
