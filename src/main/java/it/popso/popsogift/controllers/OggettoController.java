package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.OggettoOverview;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.service.OggettoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oggetto")
public class OggettoController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+OggettoController.class);
    public static final String PERFORMANCE_START="[START path=/oggetto/???]";
    public static final String PERFORMANCE_END="[END path=/oggetto/???]";
    private static Logger logger = LoggerFactory.getLogger(OggettoController.class);

    @Autowired
    private OggettoService oggettoService;

    @GetMapping("/oggettoOverview")
    public OggettoOverview getOggettoOverview(@RequestHeader("Ruolo") String ruolo,
                                                    @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata oggettoOverview");
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        OggettoOverview oggettoOverview = oggettoService.getOggettoOverview();
        performanceLog = PERFORMANCE_END.replace("???", oggettoOverview+ "\nRicerca dati oggetto completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return oggettoOverview;

    }

    @GetMapping("/all")
    public List<Oggetto> getAllOggetto(@RequestHeader("Ruolo") String ruolo,
                                       @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllOggetto");
        List<Oggetto> listaOggetto;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaOggetto = oggettoService.getAllOggetto();
        performanceLog = PERFORMANCE_END.replace("???", listaOggetto+ "\nRicerca dati oggetto completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaOggetto;

    }
}
