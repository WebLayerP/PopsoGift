package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.Esito;
import it.popso.popsogift.dto.EsitoRisposta;
import it.popso.popsogift.dto.TagDTO;
import it.popso.popsogift.entity.Tag;
import it.popso.popsogift.service.TagService;
import it.popso.popsogift.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+TagController.class);
    public static final String PERFORMANCE_START="[START path=/tag/???]";
    public static final String PERFORMANCE_END="[END path=/tag/???]";

    private static Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAllTag(@RequestHeader("Ruolo") String ruolo,
                               @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllTag");
        List<Tag> listaTag;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaTag = tagService.getAllTag();
        performanceLog = PERFORMANCE_END.replace("???", listaTag+ "\nRicerca dati tag completata in "+(System.currentTimeMillis() - start)+ Constants.MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return listaTag;
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestHeader("Ruolo") String ruolo,
                                         @RequestHeader("Matricola")String matricola,
                                         @RequestBody TagDTO tagDTO) {
        logger.info("Chiamata createTag");
        Tag tagInserito;
        String performanceLog=PERFORMANCE_START.replace("???","insert");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        tagInserito = tagService.saveTag(tagDTO, matricola);
        performanceLog = PERFORMANCE_END.replace("???", tagInserito+ "\nInserimento nuovo tag completato in "+(System.currentTimeMillis() - start)+ Constants.MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(tagInserito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EsitoRisposta> updateTag(@PathVariable Integer id,
                                                   @RequestBody TagDTO tagDTO) {
        logger.info("Chiamata modificaTag");
        String performanceLog=PERFORMANCE_START.replace("???","insert");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        TagDTO tagModificato = tagService.updateTag(tagDTO, id);
        performanceLog = PERFORMANCE_END.replace("???", tagModificato+ "\nInserimento nuovo tag completato in "+(System.currentTimeMillis() - start)+ Constants.MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        EsitoRisposta esitoRisposta = new EsitoRisposta(Esito.OK,"Tag con id " + tagModificato.getIdTag() + " modificato con successo");
        return new ResponseEntity<>(esitoRisposta, HttpStatus.CREATED);
    }
}


