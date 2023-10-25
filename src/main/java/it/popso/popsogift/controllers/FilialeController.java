package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.service.FilialeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filiale")
public class FilialeController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+FilialeController.class);
    public static final String PERFORMANCE_START="[START path=/filiale/???]";
    public static final String PERFORMANCE_END="[END path=/filiale/???]";
    private static Logger logger = LoggerFactory.getLogger(FilialeController.class);

    @Autowired
    private FilialeService filialeService;

    @GetMapping("/all")
    public List<FilialeDTO> getAllFilialeDTO(@RequestHeader("Ruolo") String ruolo,
                                          @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllFiliale");
        List<FilialeDTO> listaFiliali;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaFiliali = filialeService.getAllFilialeDTO();
        performanceLog = PERFORMANCE_END.replace("???", listaFiliali+ "\nRicerca dati stati completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaFiliali;

    }
}
