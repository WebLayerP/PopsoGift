package it.popso.popsogift.service;

import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.FornitoreMapper;
import it.popso.popsogift.repository.FornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<FornitoreDTO> findFornitoriFiltered(int page, int size, String ragioneSociale, String partitaIva) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Fornitore> risultati = fornitoreRepository.findByRagioneSocialeAndPartitaIva(ragioneSociale, partitaIva, pageable);

        return fornitoreMapper.toListFornitoreDTO(risultati.getContent());
    }

    public FornitoreDTO saveFornitore(FornitoreDTO fornitoreDTO){
        Fornitore fornitore = fornitoreMapper.fornitoreDTOToFornitore(fornitoreDTO);
        Fornitore fornitoreInserito;
        try {
            fornitoreInserito = fornitoreRepository.save(fornitore);
        } catch(org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return fornitoreMapper.fornitoreToDTO(fornitoreInserito);
    }
    public FornitoreDTO fornitoreById(Integer id) {
        Optional<Fornitore> fornitore = fornitoreRepository.findById(id);
        if(fornitore.isEmpty()){
            throw new ApplicationFaultMsgException("Il fornitore con id " + id + " non è stato trovato");
        }
        return fornitoreMapper.fornitoreToDTO(fornitore.get());
    }
    public void updateFornitore (Integer id, FornitoreDTO fornitoreDTO){
        if(fornitoreById(id)!= null) {
            fornitoreRepository.save(fornitoreMapper.fornitoreDTOToFornitore(fornitoreDTO));
        }
        else{
            throw new ApplicationFaultMsgException("Errore modifica fornitore");
        }
    }
    public Boolean deleteLogicaFornitore(Integer id, String matricola){
        Optional<Fornitore> fornitoreTrovato = fornitoreRepository.findById(id);
        if(fornitoreTrovato.isPresent()) {
            Fornitore fornitore = fornitoreTrovato.get();
            boolean stato = !fornitore.getStatoCancellazione();
            if (stato) {
                fornitore.setDataCancellazione(java.sql.Date.valueOf(LocalDate.now()));
                fornitore.setIdCancellazione(matricola);
                fornitore.setStatoCancellazione(true);
                fornitoreRepository.save(fornitore);
                return true;
            }
        }
        else{
            return false;
        }
        return false;
    }
}
