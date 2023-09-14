package it.popso.popsogift.service;


import it.popso.popsogift.dto.*;
//import it.popso.popsogift.entity.Campagna;
//import it.popso.popsogift.repository.CampagnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampagnaService {

//    @Autowired
//    private final CampagnaRepository campagnaRepository;

//    public CampagnaService(CampagnaRepository campagnaRepository) {
//        this.campagnaRepository = campagnaRepository;
//    }
//    public List<CampagnaDTO> findAllAsDTO() {
//        List<Campagna> campagne = campagnaRepository.findAll();
//        return campagne.stream()
//                .map(campagna -> new CampagnaDTO(campagna.getIdCampagna(),
//                                                campagna.getTitoloCampagna(),
//                                                campagna.getTipologia(),
//                                                campagna.getDataInizioModifiche(),
//                                                campagna.getDataFineModifiche(),
//                                                campagna.getListaOmaggi(),
//                                                campagna.getListaFiliali(),
//                                                campagna.getStato()))
//                                                .collect(Collectors.toList());
//    }


//    public List<Campagna> getAllCampagne() {
//        return campagnaRepository.findAll();
//    }
//
//    public Campagna saveCampagna(Campagna campagna) {
//        return campagnaRepository.save(campagna);
//    }
//
//    public CampagnaDTO convertEntityToDTO(Campagna campagna) {
//        CampagnaDTO campagnaDTO = new CampagnaDTO();
//        campagnaDTO.setIdCampagna(campagna.getIdCampagna());
//        campagnaDTO.setTitoloCampagna(campagna.getTitoloCampagna());
//        campagnaDTO.setStato(campagna.getStato());
//        campagnaDTO.setTipologia(campagna.getTipologia());
//        campagnaDTO.setListaFiliali(campagna.getListaFiliali());
//        campagnaDTO.setListaOmaggi(campagna.getListaOmaggi());
//        campagnaDTO.setDataFineModifiche(campagna.getDataFineModifiche());
//        campagnaDTO.setDataInizioModifiche(campagna.getDataInizioModifiche());
//        return campagnaDTO;
//    }
//
//    public Campagna saveAndConvertDTOToEntity(CampagnaDTO campagnaDTO) {
//        Campagna campagna = new Campagna();
//        campagna.setIdCampagna(campagnaDTO.getIdCampagna());
//        campagna.setTitoloCampagna(campagnaDTO.getTitoloCampagna());
//        campagna.setStato(campagnaDTO.getStato());
//        campagna.setTipologia(campagnaDTO.getTipologia());
//        campagna.setListaFiliali(campagnaDTO.getListaFiliali());
//        campagna.setListaOmaggi(campagnaDTO.getListaOmaggi());
//        campagna.setDataFineModifiche(campagnaDTO.getDataFineModifiche());
//        campagna.setDataInizioModifiche(campagnaDTO.getDataInizioModifiche());
//        campagnaRepository.save(campagna);
//        return campagna;
//    }

}