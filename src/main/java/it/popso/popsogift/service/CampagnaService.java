package it.popso.popsogift.service;


import it.popso.popsogift.dto.*;
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
import java.util.Map;

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

    public static final String DATA_INSERIMENTO = "dataInserimento";

    public List<CampagnaDTO> getAllCampagne() {
        try {
            List<Campagna> campagne = campagnaRepository.findAll();
            List<CampagnaDTO> listaCampagneDTO = new ArrayList<>();
            for (Campagna c : campagne) {
                CampagnaDTO campagnaDTO = campagnaMapper.campagnaToCampagnaDTO(c);
                List<FilialeDTO> listaFiliali = filialeMapper.listaFilialeToDTO(c.getListaFiliali());
                campagnaDTO.setListaFiliali(listaFiliali);
                listaCampagneDTO.add(campagnaDTO);
            }

            return listaCampagneDTO;
        } catch (org.springframework.transaction.CannotCreateTransactionException e) {
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }

    public Campagna saveCampagna(CampagnaDTO campagnaDTO) {
        Campagna campagna = campagnaMapper.campagnaDTOToEntity(campagnaDTO);
        campagna.setTipologia(tipologiaMapper.toTipologia(campagnaDTO.getTipologia()));
        campagna.setStato(statoMapper.toStato(campagnaDTO.getStato()));
        Campagna campagnaInserita = null;
        List<Oggetto> listaOggetti = new ArrayList<>();
        for (OggettoDTO oggettoDTO : campagnaDTO.getListaOmaggi()) {
            Oggetto oggetto = oggettoMapper.oggettoDTOToOggetto(oggettoDTO);
            try {
                oggetto.setFornitore(fornitoreMapper.fornitoreDTOToFornitore(oggettoDTO.getFornitore()));
            } catch (NullPointerException e) {
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
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return campagnaInserita;
    }

    public CampagnaListDTO listaCampagne(int page, int size, String order, String orderBy, String tipologia, String stato, String costo, String anno) {
        CampagnaListDTO campagnaListDTO = new CampagnaListDTO();
        Integer annoInt = null;
        Integer tipologiaInt = null;
        Integer statoInt = null;
        Double costoDouble = null;
        if (anno != null) {
            annoInt = Integer.parseInt(anno);
        }
        if (tipologia != null) {
            tipologiaInt = Integer.parseInt(tipologia);
        }
        if (stato != null) {
            statoInt = Integer.parseInt(stato);
        }
        if (costo != null) {
            costoDouble = Double.parseDouble(costo);
        }
        List<Campagna> campagne = campagnaRepository.findByFilters(tipologiaInt, statoInt, annoInt);
        List<CampagnaResultDTO> campagneResult = new ArrayList<>();
        List<Map<String, Object>> map;
        for (Campagna c : campagne) {
            List<Oggetto> oggetti = new ArrayList<>();
            map = campagnaRepository.findByCostoOggetti(c.getIdCampagna());
            if (map != null) {
                Double costoTot = 0d;
                for (Map<String, Object> mappa : map) {
                    if (mappa.containsKey("costo")) {
                        costoTot += (Double) mappa.get("costo");
                    }
                    if (mappa.containsKey("oggetto")) {
                        oggetti.add((Oggetto) mappa.get("oggetto"));
                    }
                }
                if (costoTot.equals(costoDouble)) {
                    CampagnaResultDTO campagnaResultDTO = new CampagnaResultDTO();
                    campagnaResultDTO.setIdCampagna(c.getIdCampagna());
                    campagnaResultDTO.setDataFineCampagna(c.getDataFine());
                    campagnaResultDTO.setDataAggiornamento(c.getDataAggiornamento());
                    campagnaResultDTO.setCosto(costoDouble);
                    campagnaResultDTO.setListaOmaggi(oggettoMapper.listaOggettiToDTO(oggetti));
                    campagnaResultDTO.setNome(c.getTitoloCampagna());
                    campagnaResultDTO.setStatus(c.getStato().getNomeStato());
                    campagnaResultDTO.setDataFineModifiche(c.getDataFineModifiche());
                    campagnaResultDTO.setListaFiliali(filialeMapper.listaFilialeToDTO(c.getListaFiliali()));
                    campagnaResultDTO.setMatricola(c.getMatricola());
                    campagnaResultDTO.setTipologia(c.getTipologia().getNomeTipologia());
                    campagneResult.add(campagnaResultDTO);
                }
                campagnaListDTO.setResults(campagneResult);
            }
        }
            return campagnaListDTO;
        }
    }