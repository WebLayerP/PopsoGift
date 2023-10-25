package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;
@Data
public class OggettoOverview {
    private long numeroOggettiDigitali;
    private long numeroOggettiFisici;
    private long numeroFornitori;
    private Date dataUltimoAggiornamento;
}
