package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.Esito;
import it.popso.popsogift.dto.EsitoRisposta;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.service.OggettoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OggettoController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+OggettoController.class);
    public static final String PERFORMANCE_START="[START path=/???]";
    public static final String PERFORMANCE_END="[END path=/???]";
    private static Logger logger = LoggerFactory.getLogger(OggettoController.class);

    @Autowired
    private OggettoService oggettoService;


    @GetMapping("/omaggi")
    public List<OggettoDTO> getAllOggetto(@RequestHeader("Ruolo") String ruolo,
                                          @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllOggetto");
        List<OggettoDTO> listaOggetto;
        String performanceLog=PERFORMANCE_START.replace("???","/omaggi");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaOggetto = oggettoService.getAllOggetto();
        performanceLog = PERFORMANCE_END.replace("???", listaOggetto+ "\nRicerca dati oggetto completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaOggetto;

    }
    @PostMapping("/omaggi")
    public ResponseEntity<EsitoRisposta> createOggetto(@RequestHeader("Ruolo") String ruolo,
                                                    @RequestHeader("Matricola")String matricola,
                                                    @RequestBody OggettoDTO oggettoDTO) {
        logger.info("Chiamata createOggetto");
        OggettoDTO oggettoInserito;
        String performanceLog=PERFORMANCE_START.replace("???","omaggi");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        oggettoInserito = oggettoService.saveOggetto(oggettoDTO);
        performanceLog = PERFORMANCE_END.replace("???", oggettoInserito+ "\nInserimento nuovo oggetto completato in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        EsitoRisposta esitoRisposta = new EsitoRisposta(Esito.OK,"Oggetto aggiunto con successo");
        return new ResponseEntity<>(esitoRisposta,HttpStatus.OK);
    }
}
