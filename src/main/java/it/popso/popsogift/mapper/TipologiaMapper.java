package it.popso.popsogift.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.dto.TipologiaDTO;
import it.popso.popsogift.entity.Tipologia;
import org.springframework.stereotype.Component;

@Component
public class TipologiaMapper {

    public TipologiaDTO getTipologiaDTO(Tipologia tipologia){
        TipologiaDTO tipologiaDTO = tipologia.getNomeTipologia();
        return tipologiaDTO;
    }

    public Tipologia getTipologia(CampagnaDTO campagnaDTO) throws JsonProcessingException {
        TipologiaDTO tipologiaDTO;
        ObjectMapper objectMapper = new ObjectMapper();
        String campagnaDTOJson= objectMapper.writeValueAsString(campagnaDTO);
        tipologiaDTO=objectMapper.readValue(campagnaDTOJson,TipologiaDTO.class);
        Tipologia tipologia = new Tipologia();
        tipologia.setIdTipologia(tipologiaDTO.getIdTipologia());
        return tipologia;
    }
}
