package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampagnaGroup {
    private Integer anno;
    private Date dataUltimoAggiornamento;
    private List<CampagnaDTO> listaCampagneConSegnalazioni;
    private int numeroCampagneInCorso;
    private int numeroCampagneChiuse;
    private int numeroCampagneBozza;
}
