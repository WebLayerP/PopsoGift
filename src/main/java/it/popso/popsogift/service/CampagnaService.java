package it.popso.popsogift.service;


import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Filiale;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.InputFaultMsgException;
import it.popso.popsogift.mapper.*;
import it.popso.popsogift.repository.CampagnaRepository;
import it.popso.popsogift.repository.FilialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampagnaService {

    @Autowired
    private CampagnaRepository campagnaRepository;

    @Autowired
    private CampagnaMapper campagnaMapper;

    @Autowired
    private FornitoreMapper fornitoreMapper;

    @Autowired
    private TipologiaMapper tipologiaMapper;

    @Autowired
    private OggettoMapper oggettoMapper;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Autowired
    private FilialeRepository filialeRepository;

    @Autowired
    private StatoMapper statoMapper;

    public List<Campagna> getAllCampagne() {
        try {
            return campagnaRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }

    public Campagna saveCampagna(CampagnaDTO campagnaDTO){
        Campagna campagna = campagnaMapper.campagnaDTOToEntity(campagnaDTO);
        campagna.setTipologia(tipologiaMapper.getTipologia(campagnaDTO));
        campagna.setStato(statoMapper.getStato(campagnaDTO));
        Campagna campagnaInserita = null;
        List<Oggetto> listaOggetti = new ArrayList<>();
        for(OggettoDTO oggettoDTO: campagnaDTO.getListaOmaggi()){
            Oggetto oggetto = oggettoMapper.oggettoDTOToOggetto(oggettoDTO);
            try {
                oggetto.setCategoria(categoriaMapper.getCategoria(oggettoDTO));
            }catch(NullPointerException e){
                throw new InputFaultMsgException("Categoria non impostata");
            }
            try {
                oggetto.setFornitore(fornitoreMapper.fornitoreDTOToFornitore(oggettoDTO));
            }catch(NullPointerException e){
                throw new InputFaultMsgException("Fornitore non impostato");
            }
            listaOggetti.add(oggetto);
        }
        campagna.setListaOmaggi(listaOggetti);
        for (Filiale filiale: campagna.getListaFiliali()) {
            if (!filialeRepository.existsById(filiale.getCodiceFiliale()))
                try {
                    filialeRepository.save(filiale);
                }catch(DataIntegrityViolationException e){
                    throw new DataIntegrityViolationException(e.getMessage());
                }
        }
        try {
            campagnaInserita = campagnaRepository.save(campagna);
        } catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return campagnaInserita;
    }
}