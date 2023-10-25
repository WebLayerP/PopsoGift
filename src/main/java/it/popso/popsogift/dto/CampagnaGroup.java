package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CampagnaGroup {
    private Integer anno;
    private Date dataUltimoAggiornamento;
    private List<CampagnaDTO> listaCampagneConSegnalazioni;
    private int numeroCampagneInCorso;
    private int numeroCampagneChiuse;
    private int numeroCampagneBozza;
    private int numeroCampagneConSegnalazioni;
}
