package it.popso.popsogift.controllers;

import it.popso.popsogift.entity.Tipologia;
import it.popso.popsogift.repository.TipologiaRepository;
import it.popso.popsogift.service.TipologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipologia")
public class TipologiaController {

    @Autowired
    private final TipologiaService tipologiaService;

    @Autowired
    TipologiaRepository tipologiaRepository;

    public TipologiaController(TipologiaService tipologiaService, TipologiaRepository tipologiaRepository) {
        this.tipologiaService = tipologiaService;
        this.tipologiaRepository = tipologiaRepository;
    }

    @Autowired
    public TipologiaController(TipologiaService tipologiaService) {
        this.tipologiaService = tipologiaService;
    }


    @GetMapping("/all")
    public List<Tipologia> getAllTipologia() {
        return tipologiaService.getAllTipologia();
    }
}

