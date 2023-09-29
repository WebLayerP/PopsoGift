package it.popso.popsogift.service;

import it.popso.popsogift.entity.Stato;
import it.popso.popsogift.repository.StatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatoService {

    @Autowired
    private final StatoRepository statoRepository;

    public StatoService(StatoRepository statoRepository) {
        this.statoRepository = statoRepository;
    }

    public List<Stato> getAllStato() {
        return statoRepository.findAll();
    }
}
