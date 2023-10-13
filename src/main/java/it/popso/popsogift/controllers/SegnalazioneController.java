package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.SegnalazioneDTO;
import it.popso.popsogift.entity.Segnalazione;
import it.popso.popsogift.repository.SegnalazioneRepository;
import it.popso.popsogift.service.SegnalazioneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/segnalazione")
public class SegnalazioneController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+ it.popso.popsogift.controllers.SegnalazioneController.class);
    public static final String PERFORMANCE_START="[START path=/segnalazione/???]";
    public static final String PERFORMANCE_END="[END path=/segnalazione/???]";
    private static Logger logger = LoggerFactory.getLogger(it.popso.popsogift.controllers.SegnalazioneController.class);

    @Autowired
    private final SegnalazioneService segnalazioneService;

    @Autowired
    SegnalazioneRepository segnalazioneRepository;

    public SegnalazioneController(SegnalazioneService segnalazioneService, SegnalazioneRepository segnalazioneRepository) {
        this.segnalazioneService = segnalazioneService;
        this.segnalazioneRepository = segnalazioneRepository;
    }

    @Autowired
    public SegnalazioneController(SegnalazioneService segnalazioneService) {
        this.segnalazioneService = segnalazioneService;
    }


    @GetMapping("/all")
    public List<Segnalazione> getAllSegnalazione(@RequestHeader("Ruolo") String ruolo,
                                                 @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllSegnalazione");
        List<Segnalazione> listaSegnalazioni;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaSegnalazioni = segnalazioneService.getAllSegnalazione();
        performanceLog = PERFORMANCE_END.replace("???", listaSegnalazioni+ "\nRicerca dati stati completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaSegnalazioni;

    }

    @PostMapping("/insert")
    public ResponseEntity<Segnalazione> createSegnalazione(@RequestHeader("Ruolo") String ruolo,
                                                   @RequestHeader("Matricola")String matricola,
                                                   @RequestBody SegnalazioneDTO segnalazioneDTO) {
        logger.info("Chiamata createSegnalazione");
        Segnalazione segnalazioneInserita;
        String performanceLog=PERFORMANCE_START.replace("???","insert");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        segnalazioneInserita = segnalazioneService.saveSegnalazione(segnalazioneDTO);
        performanceLog = PERFORMANCE_END.replace("???", segnalazioneInserita+ "\nInserimento nuova segnalazione completato in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(segnalazioneInserita, HttpStatus.CREATED);
    }

}


