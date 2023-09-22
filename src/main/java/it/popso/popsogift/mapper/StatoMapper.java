package it.popso.popsogift.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.dto.StatoDTO;
import it.popso.popsogift.dto.TipologiaDTO;
import it.popso.popsogift.entity.Stato;
import it.popso.popsogift.entity.Tipologia;
import org.springframework.stereotype.Component;
@Component
public class StatoMapper {

    public StatoDTO getStatoDTO(Stato stato){
        StatoDTO statoDTO = stato.getNomeStato();
        return statoDTO;
    }

    public Stato getStato(CampagnaDTO campagnaDTO) throws JsonProcessingException {
        StatoDTO statoDTO;
        ObjectMapper objectMapper = new ObjectMapper();
        String campagnaDTOJson= objectMapper.writeValueAsString(campagnaDTO);
        statoDTO=objectMapper.readValue(campagnaDTOJson,StatoDTO.class);
        Stato stato = new Stato();
        stato.setNomeStato(statoDTO);
        return stato;
    }
}
