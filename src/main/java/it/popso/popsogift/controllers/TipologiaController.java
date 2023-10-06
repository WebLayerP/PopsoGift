package it.popso.popsogift.controllers;

import it.popso.popsogift.entity.Tipologia;
import it.popso.popsogift.repository.TipologiaRepository;
import it.popso.popsogift.service.TipologiaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipologia")
public class TipologiaController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+TipologiaController.class);
    public static final String PERFORMANCE_START="[START path=/tipologia/???]";
    public static final String PERFORMANCE_END="[END path=/tipologia/???]";
    private static Logger logger = LoggerFactory.getLogger(TipologiaController.class);

    @Autowired
    private final TipologiaService tipologiaService;

    @Autowired
    TipologiaRepository tipologiaRepository;

    public TipologiaController(TipologiaService tipologiaService, TipologiaRepository tipologiaRepository) {
        this.tipologiaService = tipologiaService;
        this.tipologiaRepository = tipologiaRepository;
    }

    @Autowired
    public TipologiaController(TipologiaService tipologiaService) {
        this.tipologiaService = tipologiaService;
    }


    @GetMapping("/all")
    public List<Tipologia> getAllTipologia() {
        logger.info("Chiamata getAllTipologia");
        List<Tipologia> listaTipologie;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaTipologie = tipologiaService.getAllTipologia();
        performanceLog = PERFORMANCE_END.replace("???", listaTipologie+ "\nRicerca dati tipologie completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaTipologie;

    }
}

