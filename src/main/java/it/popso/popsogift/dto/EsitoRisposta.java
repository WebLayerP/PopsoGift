package it.popso.popsogift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EsitoRisposta {
    private Esito codice;
    private String messaggio;
}
