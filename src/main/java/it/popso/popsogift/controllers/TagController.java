package it.popso.popsogift.controllers;

import it.popso.popsogift.entity.Tag;
import it.popso.popsogift.repository.TagRepository;
import it.popso.popsogift.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+TagController.class);
    public static final String PERFORMANCE_START="[START path=/tag/???]";
    public static final String PERFORMANCE_END="[END path=/tag/???]";
    private static Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private final TagService tagService;

    @Autowired
    TagRepository tagRepository;

    public TagController(TagService tagService, TagRepository tagRepository) {
        this.tagService = tagService;
        this.tagRepository = tagRepository;
    }

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @GetMapping("/all")
    public List<Tag> getAllTag(@RequestHeader("Ruolo") String ruolo,
                               @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllTag");
        List<Tag> listaTag;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaTag = tagService.getAllTag();
        performanceLog = PERFORMANCE_END.replace("???", listaTag+ "\nRicerca dati tag completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaTag;


    }
}


