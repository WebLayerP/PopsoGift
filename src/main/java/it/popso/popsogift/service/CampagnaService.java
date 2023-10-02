package it.popso.popsogift.service;


import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Filiale;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.mapper.*;
import it.popso.popsogift.repository.CampagnaRepository;
import it.popso.popsogift.repository.FilialeRepository;
import it.popso.popsogift.repository.OggettoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampagnaService {

    @Autowired
    private final CampagnaRepository campagnaRepository;

    private final FilialeRepository filialeRepository;

    private final OggettoRepository oggettoRepository;

    private final TipologiaMapper tipologiaMapper;


    private final StatoMapper statoMapper;


    public CampagnaService(CampagnaRepository campagnaRepository, FilialeRepository filialeRepository, OggettoRepository oggettoRepository, TipologiaMapper tipologiaMapper, StatoMapper statoMapper) {
        this.campagnaRepository = campagnaRepository;
        this.filialeRepository = filialeRepository;
        this.oggettoRepository = oggettoRepository;
        this.tipologiaMapper = tipologiaMapper;
        this.statoMapper = statoMapper;
    }

    public List<Campagna> getAllCampagne() {
        return campagnaRepository.findAll();
    }

    public Campagna saveCampagna(CampagnaDTO campagnaDTO){
        CampagnaMapper campagnaMapper= Mappers.getMapper(CampagnaMapper.class);
        Campagna campagna = campagnaMapper.campagnaDTOToEntity(campagnaDTO);
        OggettoMapper oggettoMapper= Mappers.getMapper(OggettoMapper.class);
        FilialeMapper filialeMapper = Mappers.getMapper(FilialeMapper.class);
        List<Oggetto> listaOmaggi = campagnaDTO.getListaOmaggi().stream().map(oggettoMapper::oggettoDTOToOggetto).toList();
        List<Filiale> listaFiliali = campagnaDTO.getListaFiliali().stream().map(filialeMapper::filialeDTOToFiliale).toList();
        campagna.setTipologia(tipologiaMapper.getTipologia(campagnaDTO));
        campagna.setStato(statoMapper.getStato(campagnaDTO));
        Campagna campagnaInserita = campagnaRepository.save(campagna);
        for(Oggetto oggetto: listaOmaggi){
            oggetto.setCampagna(campagnaInserita);

        }
        for(Filiale filiale: listaFiliali){
            filiale.setCampagna(campagnaInserita);
        }
        oggettoRepository.saveAll(listaOmaggi);
        filialeRepository.saveAll(listaFiliali);
        return campagnaInserita;
    }

}