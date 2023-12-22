package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.*;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.service.CampagnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campagne")
public class CampagnaController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+CampagnaController.class);
    public static final String PERFORMANCE_START="[START path=/campagna/???]";
    public static final String PERFORMANCE_END="[END path=/campagna/???]";
    private static Logger logger = LoggerFactory.getLogger(CampagnaController.class);

    @Autowired
    private CampagnaService campagnaService;


    @Autowired
    public CampagnaController(CampagnaService campagnaService) {
        this.campagnaService = campagnaService;
    }


//    @GetMapping("/all")
//    public List<CampagnaDTO> getAllCampagne(@RequestHeader("utenza") String utenza,
//                                            @RequestHeader("matricola")String matricola) {
//        logger.info("Chiamata getAllCampagne");
//        List<CampagnaDTO> listaCampagne;
//        String performanceLog=PERFORMANCE_START.replace("???","all");
//        loggerPerformance.info(performanceLog);
//        long start = System.currentTimeMillis();
//        listaCampagne = campagnaService.getAllCampagne();
//        performanceLog = PERFORMANCE_END.replace("???", listaCampagne+ "\nRicerca dati campagne completata in "+(System.currentTimeMillis() - start)+" millisecondi");
//        loggerPerformance.debug(performanceLog);
//        return listaCampagne;
//    }
    @GetMapping
    public CampagnaListDTO getAllCampagne(          @RequestHeader("utenza") String utenza,
                                                    @RequestHeader("matricola")String matricola,
                                                    @RequestHeader("codiceFiliale") String codiceFiliale,
                                                    @RequestParam ("page") int page,
                                                    @RequestParam (value = "size")int size,
                                                    @RequestParam(value = "stato" , required = false)String stato,
                                                    @RequestParam(value = "tipologia" , required = false) String tipologia,
                                                    @RequestParam(value = "costo" , required = false) String costo,
                                                    @RequestParam(value = "anno" , required = false) String anno,
                                                    @RequestParam(value = "order", required = false) String order,
                                                    @RequestParam(value ="orderBy", required = false)String orderBy) {
        logger.info("Chiamata getAllCampagne");
        CampagnaListDTO listaCampagne;
        String performanceLog=PERFORMANCE_START.replace("???","all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaCampagne = campagnaService.listaCampagne(page,size,order,orderBy,tipologia,stato,costo,anno);
        performanceLog = PERFORMANCE_END.replace("???", listaCampagne+ "\nRicerca dati campagne completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaCampagne;
    }

    @PostMapping("/insert")
    public ResponseEntity<EsitoRisposta> createCampagna( @RequestHeader("utenza") String utenza,
                                                         @RequestHeader("matricola")String matricola,
                                                         @RequestHeader("codiceFiliale") String codiceFiliale,
                                                         @RequestBody CampagnaDTO campagnaDTO) {
        logger.info("Chiamata createCampagna");
        Campagna campagnaInserita;
        String performanceLog=PERFORMANCE_START.replace("???","insert");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        campagnaInserita = campagnaService.saveCampagna(campagnaDTO);
        performanceLog = PERFORMANCE_END.replace("???", campagnaInserita+ "\nInserimento nuova campagna completato in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        EsitoRisposta esitoRisposta = new EsitoRisposta(Esito.OK,"Campagna aggiunta con successo");
        return new ResponseEntity<>(esitoRisposta,HttpStatus.OK);
    }


    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }


}

