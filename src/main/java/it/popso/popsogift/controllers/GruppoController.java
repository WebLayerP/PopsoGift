package it.popso.popsogift.controllers;

import it.popso.popsogift.entity.Gruppo;
import it.popso.popsogift.repository.GruppoRepository;
import it.popso.popsogift.service.GruppoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gruppo")
public class GruppoController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+GruppoController.class);
    public static final String PERFORMANCE_START="[START path=/gruppo/???]";
    public static final String PERFORMANCE_END="[END path=/gruppo/???]";
    private static Logger logger = LoggerFactory.getLogger(GruppoController.class);

    @Autowired
    private final GruppoService gruppoService;

    @Autowired
    private GruppoRepository gruppoRepository;

    public GruppoController(GruppoService gruppoService, GruppoRepository gruppoRepository) {
        this.gruppoService = gruppoService;
        this.gruppoRepository = gruppoRepository;
    }

    @Autowired
    public GruppoController(GruppoService gruppoService) {
        this.gruppoService = gruppoService;
    }


    @GetMapping("/all")
    public List<Gruppo> getAllGruppo(@RequestHeader("Ruolo") String ruolo,
                                       @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllGruppo");
        List<Gruppo> listaGruppo;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaGruppo = gruppoService.getAllGruppo();
        performanceLog = PERFORMANCE_END.replace("???", listaGruppo+ "\nRicerca dati gruppo completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaGruppo;

    }
}
