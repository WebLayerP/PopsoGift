package it.popso.popsogift.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Filiale;
import it.popso.popsogift.entity.Omaggio;
import it.popso.popsogift.mapper.*;
import it.popso.popsogift.repository.CampagnaRepository;
import it.popso.popsogift.repository.FilialeRepository;
import it.popso.popsogift.repository.OmaggioRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampagnaService {

    @Autowired
    private final CampagnaRepository campagnaRepository;

    private final FilialeRepository filialeRepository;

    private final OmaggioRepository omaggioRepository;

    private final TipologiaMapper tipologiaMapper;


    private final StatoMapper statoMapper;


    public CampagnaService(CampagnaRepository campagnaRepository, FilialeRepository filialeRepository, OmaggioRepository omaggioRepository, TipologiaMapper tipologiaMapper, StatoMapper statoMapper) {
        this.campagnaRepository = campagnaRepository;
        this.filialeRepository = filialeRepository;
        this.omaggioRepository = omaggioRepository;
        this.tipologiaMapper = tipologiaMapper;
        this.statoMapper = statoMapper;
    }

    public List<Campagna> getAllCampagne() {
        return campagnaRepository.findAll();
    }

    public Campagna saveCampagna(CampagnaDTO campagnaDTO) throws JsonProcessingException {
        CampagnaMapper campagnaMapper= Mappers.getMapper(CampagnaMapper.class);
        Campagna campagna = campagnaMapper.campagnaDTOToEntity(campagnaDTO);
        OmaggioMapper omaggioMapper= Mappers.getMapper(OmaggioMapper.class);
        FilialeMapper filialeMapper = Mappers.getMapper(FilialeMapper.class);
        List<Omaggio> listaOmaggi = campagnaDTO.getListaOmaggi().stream().map(omaggioMapper::omaggioDTOToOmaggio).toList();
        List<Filiale> listaFiliali = campagnaDTO.getListaFiliali().stream().map(filialeMapper::filialeDTOToFiliale).toList();
        campagna.setTipologia(tipologiaMapper.getTipologia(campagnaDTO));
        campagna.setStato(statoMapper.getStato(campagnaDTO));
        Campagna campagnaInserita = campagnaRepository.save(campagna);
        for(Omaggio omaggio: listaOmaggi){
            omaggio.setCampagna(campagnaInserita);

        }
        for(Filiale filiale: listaFiliali){
            filiale.setCampagna(campagnaInserita);
        }
        omaggioRepository.saveAll(listaOmaggi);
        filialeRepository.saveAll(listaFiliali);
        return campagnaInserita;
    }

}