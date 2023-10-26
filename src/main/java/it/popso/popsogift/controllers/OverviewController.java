package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.PanoramicaOverview;
import it.popso.popsogift.dto.CampagnaGroup;
import it.popso.popsogift.dto.OggettoOverview;
import it.popso.popsogift.service.OverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/overview")
public class OverviewController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE." + OverviewController.class);
    public static final String PERFORMANCE_START = "[START path=/overview/???]";
    public static final String PERFORMANCE_END = "[END path=/overview/???]";
    private static Logger logger = LoggerFactory.getLogger(OverviewController.class);

    @Autowired
    private OverviewService overviewService;

    @Autowired
    public OverviewController(OverviewService overviewService) {
        this.overviewService = overviewService;
    }

    @GetMapping("/campagne")
    public CampagnaGroup getCampagneByStato(@RequestHeader("Ruolo") String ruolo,
                                            @RequestHeader("Matricola")String matricola){
        return overviewService.getCampagneOverview();
    }

    @GetMapping("/beneficiario")
    public PanoramicaOverview getAnagraficaOverview(@RequestHeader("Ruolo") String ruolo,
                                                    @RequestHeader("Matricola") String matricola) {
        logger.info("Chiamata getAnagraficaOverview");
        PanoramicaOverview panoramicaOverview;
        String performanceLog = PERFORMANCE_START.replace("???", "/getAnagraficaOverview");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        panoramicaOverview = overviewService.getAnagraficaOverview();
        performanceLog = PERFORMANCE_END.replace("???", panoramicaOverview + "\nGetAnagraficaOverview completata in " + (System.currentTimeMillis() - start) + " millisecondi");
        loggerPerformance.debug(performanceLog);
        return panoramicaOverview;

    }
    @GetMapping("/catalogo")
    public OggettoOverview getOggettoOverview(@RequestHeader("Ruolo") String ruolo,
                                              @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata oggettoOverview");
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        OggettoOverview oggettoOverview = overviewService.getOggettoOverview();
        performanceLog = PERFORMANCE_END.replace("???", oggettoOverview+ "\nRicerca dati oggetto completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return oggettoOverview;
    }
}