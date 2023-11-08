package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.Esito;
import it.popso.popsogift.dto.EsitoRisposta;
import it.popso.popsogift.dto.NotificaDTO;
import it.popso.popsogift.exceptions.ApplicationFault;
import it.popso.popsogift.service.NotificaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifica")
public class NotificheController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+NotificheController.class);
    public static final String PERFORMANCE_START="[START path=/gruppo/???]";
    public static final String PERFORMANCE_END="[END path=/gruppo/???]";

    @Autowired
    private NotificaService notificaService;

    @GetMapping("/allNotifiche")
    public List<NotificaDTO> getAllNotifiche(@RequestHeader("Ruolo") String ruolo,
                                             @RequestHeader("Matricola")String matricola,

                                             @RequestHeader("codice_Filiale") String codFiliale) {
        return notificaService.getAllNotifiche(codFiliale, ruolo, matricola);
    }

    @GetMapping("/leggiNotifica/{id}")
    public EsitoRisposta leggiNotifica(@RequestHeader("Ruolo") String ruolo,
                                       @RequestHeader("Matricola")String matricola,
                                       @PathVariable int id) {
        try {
            notificaService.leggiNotifica(matricola, id);
        }
        catch (Exception e){
            throw new ApplicationFault("Errore nella lettura della notifica");
        }
        return new EsitoRisposta(Esito.OK, "Notifica letta con successo");
    }
}
