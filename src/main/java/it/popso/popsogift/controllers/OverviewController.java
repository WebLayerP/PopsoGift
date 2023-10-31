package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.CampagnaOverview;
import it.popso.popsogift.dto.PanoramicaOverview;
import it.popso.popsogift.dto.CampagnaGroup;
import it.popso.popsogift.dto.OggettoOverview;
import it.popso.popsogift.service.OverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/overview")
public class OverviewController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE." + OverviewController.class);
    public static final String PERFORMANCE_START = "[START path=/overview/???]";
    public static final String PERFORMANCE_END = "[END path=/overview/???]";
    public static final String MILLISECONDI = " millisecondi";
    private static Logger logger = LoggerFactory.getLogger(OverviewController.class);

    @Autowired
    private OverviewService overviewService;

    @Autowired
    public OverviewController(OverviewService overviewService) {
        this.overviewService = overviewService;
    }

    @GetMapping("/campagna/{idCampagna}")
    public CampagnaOverview getCampagnaOverview(@PathVariable Integer idCampagna,
                                                @RequestHeader("Ruolo") String ruolo,
                                             @RequestHeader("Matricola")String matricola)
    {
        logger.info("Chiamata getCampagnaOverview");
        CampagnaOverview campagnaOverview;
        String performanceLog = PERFORMANCE_START.replace("???", "campagna");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        campagnaOverview = overviewService.getCampagnaOverview(idCampagna);
        performanceLog = PERFORMANCE_END.replace("???", campagnaOverview + "\nGetCampagnaOverview completata in " + (System.currentTimeMillis() - start) + MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return campagnaOverview;
    }
    @GetMapping("/campagne")
    public CampagnaGroup getCampagneOverview(@RequestHeader("Ruolo") String ruolo,
                                             @RequestHeader("Matricola")String matricola,
                                             @RequestParam List<String> codiciFiliale)
    {
        logger.info("Chiamata getCampagneOverview");
        CampagnaGroup campagnaGroup;
        String performanceLog = PERFORMANCE_START.replace("???", "campagne");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        campagnaGroup = overviewService.getCampagneOverview(ruolo,codiciFiliale);
        performanceLog = PERFORMANCE_END.replace("???", campagnaGroup + "\nGetCampagneOverview completata in " + (System.currentTimeMillis() - start) + MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return campagnaGroup;
    }

    @GetMapping("/beneficiario")
    public PanoramicaOverview getAnagraficaOverview(@RequestHeader("Ruolo") String ruolo,
                                                    @RequestHeader("Matricola") String matricola) {
        logger.info("Chiamata getAnagraficaOverview");
        PanoramicaOverview panoramicaOverview;
        String performanceLog = PERFORMANCE_START.replace("???", "beneficiario");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        panoramicaOverview = overviewService.getAnagraficaOverview();
        performanceLog = PERFORMANCE_END.replace("???", panoramicaOverview + "\nGetAnagraficaOverview completata in " + (System.currentTimeMillis() - start) + MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return panoramicaOverview;

    }
    @GetMapping("/catalogo")
    public OggettoOverview getOggettoOverview(@RequestHeader("Ruolo") String ruolo,
                                              @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata oggettoOverview");
        String performanceLog=PERFORMANCE_START.replace("???","catalogo");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        OggettoOverview oggettoOverview = overviewService.getOggettoOverview();
        performanceLog = PERFORMANCE_END.replace("???", oggettoOverview+ "\nRicerca dati oggetto completata in "+(System.currentTimeMillis() - start)+MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return oggettoOverview;
    }
}