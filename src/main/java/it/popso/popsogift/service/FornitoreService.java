package it.popso.popsogift.service;

import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.FornitoreMapper;
import it.popso.popsogift.repository.FornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FornitoreService {

    @Autowired
    private FornitoreMapper fornitoreMapper;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    public FornitoreDTO saveFornitore(FornitoreDTO fornitoreDTO){
        Fornitore fornitore = fornitoreMapper.fornitoreDTOToFornitore(fornitoreDTO);
        Fornitore fornitoreInserito = null;
        try {
            fornitoreInserito = fornitoreRepository.save(fornitore);
        } catch(org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return fornitoreMapper.fornitoreToDTO(fornitoreInserito);
    }
}
