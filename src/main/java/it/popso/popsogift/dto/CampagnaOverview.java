package it.popso.popsogift.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CampagnaOverview {

    private int tipologia;
    private int numeroOmaggi;
    private long totCosto;
    private Date dataFineCampagna;
    private int filialiConfermate;
    private int filialiInAttesa;
}
