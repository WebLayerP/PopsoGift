package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.BeneficiarioDTO;
import it.popso.popsogift.service.BeneficiarioService;
import it.popso.popsogift.service.NdgMockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {

    public static final Logger loggerPerformance = LoggerFactory.getLogger("PERFORMANCE."+BeneficiarioController.class);
    public static final String PERFORMANCE_START="[START path=/beneficiario/???]";
    public static final String PERFORMANCE_END="[END path=/beneficiario/???]";
    public static final String MILLISECONDI = " millisecondi";
    private static Logger logger = LoggerFactory.getLogger(BeneficiarioController.class);
    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private NdgMockService ndgMockService;

    @GetMapping("/all")
    public List<BeneficiarioDTO> getAllBeneficiario(@RequestHeader("Ruolo") String ruolo,
                                                 @RequestHeader("Matricola")String matricola) {
        logger.info("Chiamata getAllBeneficiario");
        List<BeneficiarioDTO> listaBeneficiario;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaBeneficiario = beneficiarioService.getAllBeneficiario();
        performanceLog = PERFORMANCE_END.replace("???", listaBeneficiario+ "\nRicerca dati beneficiario completata in "+(System.currentTimeMillis() - start)+ MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return listaBeneficiario;

    }

    @PostMapping("/insert")
    public ResponseEntity<BeneficiarioDTO> createBeneficiario(@RequestHeader("Ruolo") String ruolo,
                                                           @RequestHeader("Matricola")String matricola,
                                                           @RequestBody BeneficiarioDTO beneficiarioDTO) {
        logger.info("Chiamata createBeneficiario");
        BeneficiarioDTO beneficiarioInserito;
        beneficiarioDTO.setNdg("70");
        //beneficiarioDTO.setNdg(ndgMockService.getNdgMocked()); //TODO modifica da apportare quando avremo ANAGRAFE
        String performanceLog=PERFORMANCE_START.replace("???","insert");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        beneficiarioInserito = beneficiarioService.saveBeneficiario(beneficiarioDTO);
        performanceLog = PERFORMANCE_END.replace("???", beneficiarioInserito+ "\nInserimento nuovo beneficiario completato in "+(System.currentTimeMillis() - start)+MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(beneficiarioInserito, HttpStatus.CREATED);
    }
    @GetMapping("/dettaglio/{ndg}")
    public ResponseEntity<BeneficiarioDTO> getBeneficiarioByNdg(@RequestHeader("Ruolo") String ruolo,
                                                                @RequestHeader("Matricola")String matricola,
                                                                @PathVariable String ndg) {
        logger.info("Chiamata getBeneficiarioByNdg");
        BeneficiarioDTO beneficiarioRichiesto;
        String performanceLog = PERFORMANCE_START.replace("???", "getBeneficiarioByNdg");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        beneficiarioRichiesto = beneficiarioService.getBeneficiarioByNdg(ndg);
        performanceLog = PERFORMANCE_END.replace("???", beneficiarioRichiesto + "\nRichiesta beneficiario completata in " + (System.currentTimeMillis() - start) + MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(beneficiarioRichiesto, HttpStatus.OK);

    }

}

