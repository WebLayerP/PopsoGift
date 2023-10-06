package it.popso.popsogift.service;

import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.repository.OggettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OggettoService {

    @Autowired
    private final OggettoRepository oggettoRepository;

    public OggettoService(OggettoRepository oggettoRepository) {
        this.oggettoRepository = oggettoRepository;
    }

    public List<Oggetto> getAllOggetto() {
        try {
            return oggettoRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
}
