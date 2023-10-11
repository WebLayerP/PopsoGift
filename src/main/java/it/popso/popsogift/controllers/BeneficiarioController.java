package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.BeneficiarioDTO;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.repository.BeneficiarioRepository;
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
    private static Logger logger = LoggerFactory.getLogger(BeneficiarioController.class);
    @Autowired
    private final BeneficiarioService beneficiarioService;

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private NdgMockService ndgMockService;

    public BeneficiarioController(BeneficiarioService beneficiarioService, BeneficiarioRepository beneficiarioRepository, NdgMockService ndgMockService) {
        this.beneficiarioService = beneficiarioService;
        this.beneficiarioRepository = beneficiarioRepository;
        this.ndgMockService = ndgMockService;
    }

    @GetMapping("/all")
    public List<Beneficiario> getAllBeneficiario() {
        logger.info("Chiamata getAllBeneficiario");
        List<Beneficiario> listaBeneficiario;
        String performanceLog=PERFORMANCE_START.replace("???","/all");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        listaBeneficiario = beneficiarioService.getAllBeneficiario();
        performanceLog = PERFORMANCE_END.replace("???", listaBeneficiario+ "\nRicerca dati beneficiario completata in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return listaBeneficiario;

    }

    @PostMapping("/insert")
    public ResponseEntity<Beneficiario> createBeneficiario(@RequestBody BeneficiarioDTO beneficiarioDTO) {
        logger.info("Chiamata createBeneficiario");
        Beneficiario beneficiarioInserito;
        beneficiarioDTO.setNdg(ndgMockService.getNdgMocked()); //TODO modifica da apportare quando avremo ANAGRAFE
        String performanceLog=PERFORMANCE_START.replace("???","insert");
        loggerPerformance.info(performanceLog);
        long start = System.currentTimeMillis();
        beneficiarioInserito = beneficiarioService.saveBeneficiario(beneficiarioDTO);
        performanceLog = PERFORMANCE_END.replace("???", beneficiarioInserito+ "\nInserimento nuovo beneficiario completato in "+(System.currentTimeMillis() - start)+" millisecondi");
        loggerPerformance.debug(performanceLog);
        return new ResponseEntity<>(beneficiarioInserito, HttpStatus.CREATED);
    }
}

