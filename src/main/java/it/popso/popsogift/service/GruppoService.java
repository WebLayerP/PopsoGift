package it.popso.popsogift.service;

import it.popso.popsogift.dto.GruppoDTO;
import it.popso.popsogift.entity.Gruppo;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.mapper.BeneficiarioMapper;
import it.popso.popsogift.mapper.GruppoMapper;
import it.popso.popsogift.repository.GruppoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GruppoService {

    @Autowired
    private final GruppoRepository gruppoRepository;

    @Autowired
    private GruppoMapper gruppoMapper;
    @Autowired
    private BeneficiarioMapper beneficiarioMapper;

    public GruppoService(GruppoRepository gruppoRepository) {
        this.gruppoRepository = gruppoRepository;
    }

    public List<GruppoDTO> getAllGruppo() {
        try {
            List<Gruppo> gruppi = gruppoRepository.findAll();
            List<GruppoDTO> gruppiDTO = new ArrayList<>();
            for(Gruppo g: gruppi){
                GruppoDTO gruppoDTO = gruppoMapper.gruppoToGruppoDTO(g);
                gruppoDTO.setListaBeneficiari(beneficiarioMapper.listaBeneficiariToBeneficiariDTO(g.getListaBeneficiari()));
                gruppiDTO.add(gruppoDTO);
            }
            return gruppiDTO;
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
}

