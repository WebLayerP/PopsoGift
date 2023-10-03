package it.popso.popsogift.service;


import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.mapper.CampagnaMapper;
import it.popso.popsogift.mapper.StatoMapper;
import it.popso.popsogift.mapper.TipologiaMapper;
import it.popso.popsogift.repository.CampagnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampagnaService {

    @Autowired
    private CampagnaRepository campagnaRepository;

    @Autowired
    private CampagnaMapper campagnaMapper;

    @Autowired
    private TipologiaMapper tipologiaMapper;

    @Autowired
    private StatoMapper statoMapper;

    public List<Campagna> getAllCampagne() {
        return campagnaRepository.findAll();
    }

    public Campagna saveCampagna(CampagnaDTO campagnaDTO){
        Campagna campagna = campagnaMapper.campagnaDTOToEntity(campagnaDTO);
        campagna.setTipologia(tipologiaMapper.getTipologia(campagnaDTO));
        campagna.setStato(statoMapper.getStato(campagnaDTO));
        Campagna campagnaInserita = campagnaRepository.save(campagna);
        return campagnaInserita;
    }

}