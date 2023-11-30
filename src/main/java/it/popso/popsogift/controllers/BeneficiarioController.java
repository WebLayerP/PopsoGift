package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.*;
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
@RequestMapping("/beneficiari")
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

    @PostMapping
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
    @GetMapping("/{ndg}")
    public ResponseEntity<BeneficiarioDettaglioDTO> getBeneficiarioByNdg(@RequestHeader("Ruolo") String ruolo,
                                                                @RequestHeader("Matricola")String matricola,
                                                                @PathVariable String ndg) {
        logger.info("Chiamata getBeneficiarioByNdg");
        BeneficiarioDettaglioDTO beneficiarioRichiesto;
        String performanceLog = PERFORMANCE_START.replace("???", "getBeneficiarioByNdg");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        beneficiarioRichiesto = beneficiarioService.getBeneficiarioByNdg(ndg);
        performanceLog = PERFORMANCE_END.replace("???", beneficiarioRichiesto + "\nRichiesta beneficiario completata in " + (System.currentTimeMillis() - start) + MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(beneficiarioRichiesto, HttpStatus.OK);

    }

    @PutMapping("/{ndg}")
    public ResponseEntity<EsitoRisposta> updateBeneficiario(@RequestHeader("Ruolo") String ruolo,
                                                         @RequestHeader("Matricola")String matricola,
                                                         @PathVariable String ndg,
                                                         @RequestBody BeneficiarioDettaglioDTO beneficiarioDettaglioDTO) {
        logger.info("Chiamata modificaBeneficiario");
        String performanceLog=PERFORMANCE_START.replace("???","modificaBeneficiario");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        beneficiarioService.updateBeneficiario(ndg,beneficiarioDettaglioDTO);
        performanceLog = PERFORMANCE_END.replace("???", beneficiarioDettaglioDTO+ "\nModifica beneficiario completata in "+(System.currentTimeMillis() - start)+MILLISECONDI);
        loggerPerformance.debug(performanceLog);
        EsitoRisposta esitoRisposta = new EsitoRisposta(Esito.OK,"Beneficiario modificato con successo");
        return new ResponseEntity<>(esitoRisposta,HttpStatus.OK);
    }

}

