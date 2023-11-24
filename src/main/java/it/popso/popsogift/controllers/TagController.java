package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.*;
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
    public ResponseEntity<TagListDTO> getAllTag(@RequestParam ("page") int page,
                                                @RequestParam (value = "size")int size,
                                                @RequestParam(value = "order", required = false) String order,
                                                @RequestParam(value ="orderBy", required = false)String orderBy) {
        logger.info("Chiamata getAllTag");
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        TagListDTO result = tagService.getAllTag(page,size,order,orderBy);
        performanceLog = PERFORMANCE_END.replace("???", result + "\nRicerca dati tag completata in "+(System.currentTimeMillis() - start)+ Constants.MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/lista-dinamica")
    public ResponseEntity<List<TagDTO>> ricercaDinamica(@RequestParam ("tag") String tag) {
        logger.info("Chiamata ricerca dinamica");
        String performanceLog=PERFORMANCE_START.replace("???","tag/lista-dinamica");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        List<TagDTO> result = tagService.dynamicSearch(tag.toUpperCase());
        performanceLog = PERFORMANCE_END.replace("???", result + "\nRicerca dinamica tag completata in "+(System.currentTimeMillis() - start)+ Constants.MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
        String performanceLog=PERFORMANCE_START.replace("???","update");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        TagDTO tagModificato = tagService.updateTag(tagDTO, id);
        performanceLog = PERFORMANCE_END.replace("???", tagModificato+ "\nInserimento nuovo tag completato in "+(System.currentTimeMillis() - start)+ Constants.MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        EsitoRisposta esitoRisposta = new EsitoRisposta(Esito.OK,"Tag con id " + tagModificato.getIdTag() + " modificato con successo");
        return new ResponseEntity<>(esitoRisposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagOutputDTO> findTag(@PathVariable Integer id) {
        logger.info("Chiamata findTagById");
        String performanceLog=PERFORMANCE_START.replace("???","findById");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        TagOutputDTO tag = tagService.findTagById(id);
        performanceLog = PERFORMANCE_END.replace("???", tag+ "\nricerca tag per id completato in "+(System.currentTimeMillis() - start)+ Constants.MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}


