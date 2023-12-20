package it.popso.popsogift.service;

import it.popso.popsogift.dto.StatoDTO;
import it.popso.popsogift.entity.Stato;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.repository.StatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatoService {

    @Autowired
    private StatoRepository statoRepository;

    public List<StatoDTO> getAllStato() {
        try{
            List<Stato> stati = statoRepository.findAll();
            List<StatoDTO> statiCampagnaDTO = new ArrayList<>();
            for (Stato s: stati){
                StatoDTO statoDTO = new StatoDTO();
                statoDTO.setIdStato(s.getIdStato());
                statoDTO.setNomeStato(s.getNomeStato());
                statiCampagnaDTO.add(statoDTO);
            }
            return statiCampagnaDTO;
        }catch(CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
}
