package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.Esito;
import it.popso.popsogift.dto.EsitoRisposta;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.dto.OmaggiListDTO;
import it.popso.popsogift.service.OggettoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/omaggi")
public class OggettoController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+OggettoController.class);
    public static final String PERFORMANCE_START="[START path=/???]";
    public static final String PERFORMANCE_END="[END path=/???]";
    public static final String MILLISECONDI = " millisecondi";
    private static Logger logger = LoggerFactory.getLogger(OggettoController.class);

    @Autowired
    private OggettoService oggettoService;


    @GetMapping
    public ResponseEntity<OmaggiListDTO> listaOmaggi(@RequestParam("page") int page,
                                                       @RequestParam (value = "size")int size,
                                                       @RequestParam(value = "tipologia" , required = false)String tipologia,
                                                       @RequestParam(value = "fornitore" , required = false) String fornitore,
                                                       @RequestParam(value = "tag" , required = false) String tag,
                                                       @RequestParam(value = "order", required = false) String order,
                                                       @RequestParam(value ="orderBy", required = false)String orderBy) {
        logger.info("Chiamata listaOmaggi");
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        OmaggiListDTO result = oggettoService.listaOmaggi(page, size, order, orderBy, tipologia, tag, fornitore);
        performanceLog = PERFORMANCE_END.replace("???", result+ "\nRicerca lista oggetto completata in "+(System.currentTimeMillis() - start)+ MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<OggettoDTO> omaggioDetail(@PathVariable Integer id) {
        logger.info("Chiamata omaggioById");
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        OggettoDTO result = oggettoService.omaggioDetail(id);
        performanceLog = PERFORMANCE_END.replace("???", result+ "\nRicerca completata in "+(System.currentTimeMillis() - start)+ MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/all")
    public List<OggettoDTO> getAllOggetto(@RequestHeader("Ruolo") String ruolo,
                                       @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllOggetto");
        List<OggettoDTO> listaOggetto;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaOggetto = oggettoService.getAllOggetto();
        performanceLog = PERFORMANCE_END.replace("???", listaOggetto+ "\nRicerca lista omaggi completa completata in "+(System.currentTimeMillis() - start)+ MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return listaOggetto;

    }
    @PostMapping
    public ResponseEntity<EsitoRisposta> createOggetto(@RequestHeader("Ruolo") String ruolo,
                                                    @RequestHeader("Matricola")String matricola,
                                                    @RequestBody OggettoDTO oggettoDTO) {
        logger.info("Chiamata createOggetto");
        OggettoDTO oggettoInserito;
        String performanceLog=PERFORMANCE_START.replace("???","omaggi");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        oggettoInserito = oggettoService.saveOggetto(oggettoDTO);
        performanceLog = PERFORMANCE_END.replace("???", oggettoInserito+ "\nInserimento nuovo oggetto completato in "+(System.currentTimeMillis() - start)+ MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        EsitoRisposta esitoRisposta = new EsitoRisposta(Esito.OK,"Oggetto aggiunto con successo");
        return new ResponseEntity<>(esitoRisposta,HttpStatus.OK);
    }
}
