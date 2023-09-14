package it.popso.popsogift.controllers;

import it.popso.popsogift.dto.CampagnaDTO;
//import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.service.CampagnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campagna")
public class CampagnaController {
    @Autowired
    private final CampagnaService campagnaService;

    @Autowired
    public CampagnaController(CampagnaService campagnaService) {
        this.campagnaService = campagnaService;
    }


//    @GetMapping("/prova/entities")
//    public List<CampagnaDTO> getAllCampagne() {
//        return campagnaService.findAllAsDTO();
//    }

//    @PostMapping("/prova/entity")
//    public Campagna createCampagnaDTO(@RequestBody CampagnaDTO campagnaDTO) {
//        return campagnaService.saveAndConvertDTOToEntity(campagnaDTO);
//    }


    @GetMapping("/test")
    public String testEndpoint() {
        return "OK";
    }

}

