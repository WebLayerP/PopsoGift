package it.popso.popsogift.service;

import it.popso.popsogift.dto.SegnalazioneDTO;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Segnalazione;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.exceptions.InputFaultMsgException;
import it.popso.popsogift.mapper.*;
import it.popso.popsogift.repository.SegnalazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SegnalazioneService {

    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    @Autowired
    private SegnalazioneMapper segnalazioneMapper;

    @Autowired
    private CampagnaMapper campagnaMapper;

    @Autowired
    private BeneficiarioMapper beneficiarioMapper;

    public List<Segnalazione> getAllSegnalazione() {
        try{
            return segnalazioneRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
    public Segnalazione saveSegnalazione(SegnalazioneDTO segnalazioneDTO){
        Segnalazione segnalazione = segnalazioneMapper.segnalazioneDTOToEntity(segnalazioneDTO);
        Campagna campagna =campagnaMapper.campagnaDTOToEntity(segnalazioneDTO.getCampagna());
        Segnalazione segnalazioneInserita;
        try{
            segnalazione.setCampagna(campagna);
        }catch(NullPointerException e){
            throw new InputFaultMsgException(("Campagna non impostata"));
        }
        Beneficiario beneficiario =beneficiarioMapper.beneficiarioDTOToBeneficiario(segnalazioneDTO.getBeneficiario());
        try {
            segnalazione.setBeneficiario(beneficiario);
        }catch(NullPointerException e){
            throw new InputFaultMsgException("Beneficiario non impostato");
        }
        try {
            segnalazione.setDataInserimento(new Date());
            segnalazioneInserita = segnalazioneRepository.save(segnalazione);
        } catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return segnalazioneInserita;
    }
}

