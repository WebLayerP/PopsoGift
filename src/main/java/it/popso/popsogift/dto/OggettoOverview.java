package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OggettoOverview {
    private long numeroOggettiDigitali;
    private long numeroOggettiFisici;
    private long numeroFornitori;
    private Date dataUltimoAggiornamento;
}
