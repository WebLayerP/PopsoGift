package it.popso.popsogift.service;


import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
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
    private FilialeMapper filialeMapper;
    @Autowired
    private FornitoreMapper fornitoreMapper;

    @Autowired
    private TipologiaMapper tipologiaMapper;

    @Autowired
    private OggettoMapper oggettoMapper;

    @Autowired
    private FilialeRepository filialeRepository;

    @Autowired
    private StatoMapper statoMapper;

    public List<CampagnaDTO> getAllCampagne() {
        try {
            List<Campagna> campagne = campagnaRepository.findAll();
            List<CampagnaDTO> listaCampagneDTO = new ArrayList<>();
            for(Campagna c: campagne){
                CampagnaDTO campagnaDTO = campagnaMapper.campagnaToCampagnaDTO(c);
                List<FilialeDTO> listaFiliali = filialeMapper.listaFilialeToDTO(c.getListaFiliali());
                campagnaDTO.setListaFiliali(listaFiliali);
                listaCampagneDTO.add(campagnaDTO);
            }

            return listaCampagneDTO;
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }

    public Campagna saveCampagna(CampagnaDTO campagnaDTO){
        Campagna campagna = campagnaMapper.campagnaDTOToEntity(campagnaDTO);
        campagna.setTipologia(tipologiaMapper.toTipologia(campagnaDTO.getTipologia()));
        campagna.setStato(statoMapper.toStato(campagnaDTO.getStato()));
        Campagna campagnaInserita = null;
        List<Oggetto> listaOggetti = new ArrayList<>();
        for(OggettoDTO oggettoDTO: campagnaDTO.getListaOmaggi()){
            Oggetto oggetto = oggettoMapper.oggettoDTOToOggetto(oggettoDTO);
            try {
                oggetto.setFornitore(fornitoreMapper.fornitoreDTOToFornitore(oggettoDTO.getFornitore()));
            }catch(NullPointerException e){
                throw new InputFaultMsgException("Fornitore non impostato");
            }
            listaOggetti.add(oggetto);
        }
        campagna.setListaOmaggi(listaOggetti);
        for (FilialeDTO filialeDTO : campagnaDTO.getListaFiliali()) {
            if (!filialeRepository.existsById(filialeDTO.getCodiceFiliale()))
                try {
                    filialeRepository.save(filialeMapper.filialeDTOToFiliale(filialeDTO));
                } catch (DataIntegrityViolationException e) {
                    throw new DataIntegrityViolationException(e.getMessage());
                }

            campagna.setListaFiliali(filialeMapper.listaFilialeDTOToEntity(campagnaDTO.getListaFiliali()));
        }
        try {
            campagnaInserita = campagnaRepository.save(campagna);
        } catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return campagnaInserita;
    }
}