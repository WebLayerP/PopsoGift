package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.service.FornitoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornitori")
public class FornitoreController {

    @Autowired
    private FornitoreService fornitoreService;

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+FornitoreController.class);
    public static final String PERFORMANCE_START="[START path=/fornitore/???]";
    public static final String PERFORMANCE_END="[END path=/fornitore/???]";
    private static Logger logger = LoggerFactory.getLogger(FornitoreController.class);

    @PostMapping("/aggiungiFornitore")
    public ResponseEntity<FornitoreDTO> createFornitore(@RequestHeader("Ruolo") String ruolo,
                                                        @RequestHeader("Matricola")String matricola,
                                                        @RequestBody FornitoreDTO fornitoreDTO) {
        logger.info("Chiamata aggiungiFornitore");
        FornitoreDTO fornitoreInserito;
        String performanceLog=PERFORMANCE_START.replace("???","insert");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        fornitoreInserito = fornitoreService.saveFornitore(fornitoreDTO);
        performanceLog = PERFORMANCE_END.replace("???", fornitoreInserito+ "\nInserimento nuovo fornitore completato in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(fornitoreInserito, HttpStatus.CREATED);
    }
}
