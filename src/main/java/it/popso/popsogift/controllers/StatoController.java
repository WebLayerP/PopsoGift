package it.popso.popsogift.controllers;

import it.popso.popsogift.entity.Stato;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.repository.StatoRepository;
import it.popso.popsogift.service.StatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/stato")
public class StatoController {

    @Autowired
    private final StatoService statoService;

    @Autowired
    StatoRepository statoRepository;

    public StatoController(StatoService statoService, StatoRepository statoRepository) {
        this.statoService = statoService;
        this.statoRepository = statoRepository;
    }

    @Autowired
    public StatoController(StatoService statoService) {
        this.statoService = statoService;
    }


    @GetMapping("/all")
    public List<Stato> getAllStato() {
        try{
            return statoService.getAllStato();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
}
