package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.repository.CampagnaRepository;
import it.popso.popsogift.service.CampagnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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


    @GetMapping("/all")
    public List<Campagna> getAllCampagne() {
        return campagnaService.getAllCampagne();
    }

    @PostMapping("/insert")
    public ResponseEntity<?> createCampagna(@RequestBody CampagnaDTO campagnaDTO){
        try {
            Campagna campagnaInserita = campagnaService.saveCampagna(campagnaDTO);
            return new ResponseEntity<>(campagnaInserita, HttpStatus.CREATED);
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore: campagna duplicata");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno del server");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

}

