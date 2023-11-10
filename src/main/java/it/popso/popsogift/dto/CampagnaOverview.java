package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampagnaOverview {

    private int tipologia;
    private int numeroOmaggi;
    private long totCosto;
    private Date dataFineCampagna;
    private int filialiConfermate;
    private int filialiInAttesa;
}
