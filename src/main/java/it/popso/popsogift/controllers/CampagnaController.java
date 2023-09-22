package it.popso.popsogift.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.dto.OmaggioDTO;
import it.popso.popsogift.dto.TipologiaDTO;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Tipologia;
import it.popso.popsogift.repository.CampagnaRepository;
import it.popso.popsogift.service.CampagnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campagna")
public class CampagnaController {
    @Autowired
    private final CampagnaService campagnaService;

    @Autowired
    CampagnaRepository campagnaRepository;

    public CampagnaController(CampagnaService campagnaService, CampagnaRepository campagnaRepository) {
        this.campagnaService = campagnaService;
        this.campagnaRepository = campagnaRepository;
    }

    @Autowired
    public CampagnaController(CampagnaService campagnaService) {
        this.campagnaService = campagnaService;
    }


    @GetMapping("/prova/entities")
    public List<Campagna> getAllCampagne() {
        return campagnaService.getAllCampagne();
    }

    @PostMapping("/prova/inserisci")
    public ResponseEntity<Campagna> createCampagna(@RequestBody CampagnaDTO campagnaDTO) throws JsonProcessingException {
        Campagna campagna = new Campagna();
        Tipologia tipologia = new Tipologia();
        tipologia.setIdTipologia(campagnaDTO.getTipologia().getIdTipologia());
        tipologia.setNomeTipologia(TipologiaDTO.valueOf(campagnaDTO.getTipologia().name()));
        campagna.setTipologia(tipologia);
        Campagna campagnaInserita = campagnaRepository.save(campagna);

        //CampagnaDTO campagnaInserita = campagnaService.saveCampagna(campagnaDTO);
        return new ResponseEntity<>(campagnaInserita, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

}

