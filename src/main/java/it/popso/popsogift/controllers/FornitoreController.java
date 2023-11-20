package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.Esito;
import it.popso.popsogift.dto.EsitoRisposta;
import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.dto.FornitoreListDTO;
import it.popso.popsogift.exceptions.ApplicationFault;
import it.popso.popsogift.service.FornitoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornitori")
public class FornitoreController {

    public static final String MILLISECONDI = " millisecondi";
    @Autowired
    private FornitoreService fornitoreService;

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+FornitoreController.class);
    public static final String PERFORMANCE_START="[START path=/fornitore/???]";
    public static final String PERFORMANCE_END="[END path=/fornitore/???]";
    private static Logger logger = LoggerFactory.getLogger(FornitoreController.class);

    @GetMapping
    public ResponseEntity<FornitoreListDTO> listaFornitori(@RequestParam ("page") int page,
                                                           @RequestParam (value = "size")int size,
                                                           @RequestParam(value = "ragioneSociale" , required = false)String ragioneSociale,
                                                           @RequestParam(value = "partitaIva" , required = false) String partitaIva,
                                                           @RequestParam(value = "order", required = false) String order,
                                                           @RequestParam(value ="orderBy", required = false)String orderBy)

    {
        try{
            logger.info("Chiamata Lista Fornitori Ordered");
            String performanceLog=PERFORMANCE_START.replace("???","all");
            loggerPerformance.info(performanceLog);
            long start = System.currentTimeMillis();
            FornitoreListDTO listaFornitori = fornitoreService.listaFornitori(page, size, order, orderBy,ragioneSociale, partitaIva);
            performanceLog = PERFORMANCE_END.replace("???", listaFornitori+ "\nRicerca ordinata fornitori completata in "+(System.currentTimeMillis() - start)+ MILLISECONDI);
            loggerPerformance.debug(performanceLog);
            return new ResponseEntity<>(listaFornitori, HttpStatus.OK);
        }catch (Exception e){
            throw new ApplicationFault(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<FornitoreDTO> createFornitore(@RequestHeader("Ruolo") String ruolo,
                                                        @RequestHeader("Matricola")String matricola,
                                                        @RequestBody FornitoreDTO fornitoreDTO) {
        logger.info("Chiamata aggiungiFornitore");
        FornitoreDTO fornitoreInserito;
        String performanceLog=PERFORMANCE_START.replace("???","aggiungiFornitore");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        fornitoreInserito = fornitoreService.saveFornitore(fornitoreDTO);
        performanceLog = PERFORMANCE_END.replace("???", fornitoreInserito+ "\nInserimento nuovo fornitore completato in "+(System.currentTimeMillis() - start)+ MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(fornitoreInserito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EsitoRisposta> updateFornitore(@RequestHeader("Ruolo") String ruolo,
                                                         @RequestHeader("Matricola")String matricola,
                                                         @PathVariable Integer id,
                                                         @RequestBody FornitoreDTO fornitoreDTO) {
        logger.info("Chiamata modificaFornitore");
        String performanceLog=PERFORMANCE_START.replace("???","modificaFornitore");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        fornitoreService.updateFornitore(id,fornitoreDTO);
        performanceLog = PERFORMANCE_END.replace("???", fornitoreDTO+ "\nModifica fornitore completata in "+(System.currentTimeMillis() - start)+MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        EsitoRisposta esitoRisposta = new EsitoRisposta(Esito.OK,"Fornitore modificato con successo");
        return new ResponseEntity<>(esitoRisposta,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornitoreDTO> getFornitoreById(@RequestHeader("Ruolo") String ruolo,
                                                         @RequestHeader("Matricola")String matricola,
                                                         @PathVariable Integer id) {
        logger.info("Chiamata getFornitoreById");
        FornitoreDTO fornitoreRichiesto;
        String performanceLog = PERFORMANCE_START.replace("???", "getFornitoreById");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        fornitoreRichiesto = fornitoreService.fornitoreById(id);
        performanceLog = PERFORMANCE_END.replace("???", fornitoreRichiesto + "\nRichiesta fornitore completata in " + (System.currentTimeMillis() - start) + MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(fornitoreRichiesto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EsitoRisposta> cancellaFornitore(@RequestHeader("Ruolo") String ruolo,
                                                           @RequestHeader("Matricola")String matricola,
                                                           @PathVariable Integer id) {
        logger.info("Chiamata cancellaFornitore");
        EsitoRisposta esitoRisposta;
        String performanceLog=PERFORMANCE_START.replace("???","cancellaFornitore");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        HttpStatus status;
        fornitoreService.deleteLogicaFornitore(id,matricola);
        esitoRisposta =new EsitoRisposta(Esito.OK,"Fornitore cancellato con successo");
        status = HttpStatus.OK;
        performanceLog = PERFORMANCE_END.replace("???", "Chiamata cancellazione fornitore con id: " + id + " effettuata in "+(System.currentTimeMillis() - start)+MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(esitoRisposta,status);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<FornitoreDTO>> getFornitori(@RequestHeader("Ruolo") String ruolo,
                                                           @RequestHeader("Matricola")String matricola)

    {
        try{
            logger.info("Chiamata getFornitori");
            String performanceLog=PERFORMANCE_START.replace("???","fornitori");
            loggerPerformance.info(performanceLog);
            long start = System.currentTimeMillis();
            List<FornitoreDTO> listaFornitori = fornitoreService.getFornitori();
            performanceLog = PERFORMANCE_END.replace("???", listaFornitori+ "\nRicerca fornitori completata in "+(System.currentTimeMillis() - start)+ MILLISECONDI);
            loggerPerformance.debug(performanceLog);
            return new ResponseEntity<>(listaFornitori, HttpStatus.OK);
        }catch (Exception e){
            throw new ApplicationFault(e.getMessage());
        }
    }
}
