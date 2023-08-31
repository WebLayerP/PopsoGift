package it.popso.popsogift.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fault {

    private ClasseFault classeFault;
    private String codice;
    private String messaggio;
    private String layer;


    public enum ClasseFault{
        APPLICATION_FAULT,
        DATI_TESTATA_FAULT,
        INPUT_FAULT,
        SERVIZIO_NON_DISPONIBILE_FAULT,
        SYSTEM_FAULT,
        DUBBIA_RISOTTOMISSIBILE_FAULT,
        DUBBIA_NON_RISOTTOMISSIBILE_FAULT,
        DUBBIA_RISOTTOMISSIBILE_CON_INTERVENTO_MANUALE_FAULT,
        VALIDATION_FAULT
    }

}