package it.popso.popsogift.service;

import it.popso.popsogift.entity.Segnalazione;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.repository.SegnalazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegnalazioneService {

    @Autowired
    private final SegnalazioneRepository segnalazioneRepository;

    public SegnalazioneService(SegnalazioneRepository segnalazioneRepository) {
        this.segnalazioneRepository = segnalazioneRepository;
    }

    public List<Segnalazione> getAllSegnalazione() {
        try{
            return segnalazioneRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
}

