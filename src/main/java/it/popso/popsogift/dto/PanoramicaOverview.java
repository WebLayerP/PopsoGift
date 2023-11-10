package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanoramicaOverview {

    private int numeroBeneficiari;
    private Date dataAggiornamento;
    private int numeroPrivacyKO;
    private int numeroMailKO;
    private int numeroIndirizziKO;
}
