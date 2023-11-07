package it.popso.popsogift.service;

import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.FornitoreMapper;
import it.popso.popsogift.repository.FornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornitoreService {

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private FornitoreMapper fornitoreMapper;

    public List<FornitoreDTO> findFornitoriOrdered(int page, int size, String order, String orderBy) {
        Pageable pageable;
        if(order.equals("ASC"))
            pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());
        else
            pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());

        Page<Fornitore> risultati = fornitoreRepository.findAll(pageable);

        return fornitoreMapper.toListFornitoreDTO(risultati.getContent());
    }
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
