package it.popso.popsogift.service;

import it.popso.popsogift.dto.OggettoOverview;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.repository.FornitoreRepository;
import it.popso.popsogift.repository.OggettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OggettoService {

    @Autowired
    private OggettoRepository oggettoRepository;

    @Autowired
    private FornitoreRepository fornitoreRepository;


    public List<Oggetto> getAllOggetto() {
        try {
            return oggettoRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }

    public OggettoOverview getOggettoOverview(){
        OggettoOverview result = new OggettoOverview();
        List<Object[]> risultatiQuery = oggettoRepository.numeroOggettiGroupByTipologia();
        for (Object[] o : risultatiQuery) {
            if((int) o[0] == 1 )
                result.setNumeroOggettiFisici((long)o[1]);
            else if ((int) o[0] == 2 )
                result.setNumeroOggettiDigitali((long)o[1]);
            }
        result.setNumeroFornitori(fornitoreRepository.findNumeroFornitoriTorali());
        result.setDataUltimoAggiornamento(oggettoRepository.findMaxByDataAggiornamento());
        return result;
        }
    }
