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

import java.util.List;

@Service
public class SegnalazioneService {

    @Autowired
    private final SegnalazioneRepository segnalazioneRepository;

    @Autowired
    private final SegnalazioneMapper segnalazioneMapper;

    @Autowired
    private final CampagnaMapper campagnaMapper;

    @Autowired
    private final TipologiaMapper tipologiaMapper;

    @Autowired
    private final StatoMapper statoMapper;

    @Autowired
    private final FornitoreMapper fornitoreMapper;

    @Autowired
    private final CategoriaMapper categoriaMapper;
    @Autowired
    private final BeneficiarioMapper beneficiarioMapper;

    @Autowired
    private final StatoBeneficiarioMapper statoBeneficiarioMapper;

    @Autowired
    private final OggettoMapper oggettoMapper;

    public SegnalazioneService(SegnalazioneRepository segnalazioneRepository, SegnalazioneMapper segnalazioneMapper, CampagnaMapper campagnaMapper, TipologiaMapper tipologiaMapper, StatoMapper statoMapper, FornitoreMapper fornitoreMapper, CategoriaMapper categoriaMapper, BeneficiarioMapper beneficiarioMapper, StatoBeneficiarioMapper statoBeneficiarioMapper, OggettoMapper oggettoMapper) {
        this.segnalazioneRepository = segnalazioneRepository;
        this.segnalazioneMapper = segnalazioneMapper;
        this.campagnaMapper = campagnaMapper;
        this.tipologiaMapper = tipologiaMapper;
        this.statoMapper = statoMapper;
        this.fornitoreMapper = fornitoreMapper;
        this.categoriaMapper = categoriaMapper;
        this.beneficiarioMapper = beneficiarioMapper;
        this.statoBeneficiarioMapper = statoBeneficiarioMapper;
        this.oggettoMapper = oggettoMapper;
    }

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
        Segnalazione segnalazioneInserita = null;
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
            segnalazioneInserita = segnalazioneRepository.save(segnalazione);
        } catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return segnalazioneInserita;
    }
}

