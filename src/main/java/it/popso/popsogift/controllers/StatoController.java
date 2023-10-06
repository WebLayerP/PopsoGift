package it.popso.popsogift.controllers;

import it.popso.popsogift.entity.Stato;
import it.popso.popsogift.repository.StatoRepository;
import it.popso.popsogift.service.StatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/stato")
public class StatoController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+StatoController.class);
    public static final String PERFORMANCE_START="[START path=/stato/???]";
    public static final String PERFORMANCE_END="[END path=/stato/???]";
    private static Logger logger = LoggerFactory.getLogger(StatoController.class);

    @Autowired
    private final StatoService statoService;

    @Autowired
    StatoRepository statoRepository;

    public StatoController(StatoService statoService, StatoRepository statoRepository) {
        this.statoService = statoService;
        this.statoRepository = statoRepository;
    }

    @Autowired
    public StatoController(StatoService statoService) {
        this.statoService = statoService;
    }


    @GetMapping("/all")
    public List<Stato> getAllStato() {
        logger.info("Chiamata getAllStato");
        List<Stato> listaStati;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaStati = statoService.getAllStato();
        performanceLog = PERFORMANCE_END.replace("???", listaStati+ "\nRicerca dati stati completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaStati;

    }
}
