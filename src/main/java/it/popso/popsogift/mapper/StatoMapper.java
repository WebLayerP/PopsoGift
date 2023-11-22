package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.dto.StatoDTO;
import it.popso.popsogift.entity.Stato;
import org.springframework.stereotype.Component;
@Component
public class StatoMapper {

    public Stato getStato(CampagnaDTO campagnaDTO){
        StatoDTO statoDTO = campagnaDTO.getStato();
        Stato stato = new Stato();
        stato.setIdStato(statoDTO.getIdStato());
        stato.setNomeStato(statoDTO);
        return stato;
    }
}
