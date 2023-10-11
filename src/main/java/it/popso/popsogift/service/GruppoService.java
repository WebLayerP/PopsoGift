package it.popso.popsogift.service;

import it.popso.popsogift.entity.Gruppo;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.repository.GruppoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GruppoService {

    @Autowired
    private final GruppoRepository gruppoRepository;

    public GruppoService(GruppoRepository gruppoRepository) {
        this.gruppoRepository = gruppoRepository;
    }

    public List<Gruppo> getAllGruppo() {
        try {
            return gruppoRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
}

