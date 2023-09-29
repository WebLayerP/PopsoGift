package it.popso.popsogift.service;

import it.popso.popsogift.entity.Tipologia;
import it.popso.popsogift.repository.TipologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipologiaService {

    @Autowired
    private final TipologiaRepository tipologiaRepository;

    public TipologiaService(TipologiaRepository tipologiaRepository) {
        this.tipologiaRepository = tipologiaRepository;
    }

    public List<Tipologia> getAllTipologia() {
        return tipologiaRepository.findAll();
    }
}
