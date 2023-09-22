package it.popso.popsogift.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.entity.Filiale;
import it.popso.popsogift.entity.Omaggio;
import it.popso.popsogift.mapper.CampagnaMapper;
import it.popso.popsogift.repository.CampagnaRepository;
import it.popso.popsogift.repository.FilialeRepository;
import it.popso.popsogift.repository.OmaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampagnaService {

    @Autowired
    private final CampagnaRepository campagnaRepository;

    private final FilialeRepository filialeRepository;

    private final OmaggioRepository omaggioRepository;
    private final CampagnaMapper campagnaMapper;

    public CampagnaService(CampagnaRepository campagnaRepository, CampagnaMapper campagnaMapper,
                           FilialeRepository filialeRepository, OmaggioRepository omaggioRepository) {
        this.campagnaRepository = campagnaRepository;
        this.campagnaMapper = campagnaMapper;
        this.filialeRepository= filialeRepository;
        this.omaggioRepository= omaggioRepository;
    }

    public List<Campagna> getAllCampagne() {
        return campagnaRepository.findAll();
    }

    public CampagnaDTO saveCampagna(CampagnaDTO campagnaDTO) throws JsonProcessingException {
        Campagna campagna = campagnaMapper.campagnaDTOToEntity(campagnaDTO);
        List<Omaggio> listaOmaggi = campagna.getListaOmaggi();
        List<Filiale> listaFiliali = campagna.getListaFiliali();
        Campagna campagnaInserita = campagnaRepository.save(campagna);
        for(Omaggio omaggio: listaOmaggi){
            omaggioRepository.save(omaggio);
        }
        for(Filiale filiale: listaFiliali){
            filialeRepository.save(filiale);
        }
        return campagnaMapper.getCampagnaDTO(campagnaInserita);
    }

}