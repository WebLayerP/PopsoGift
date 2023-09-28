package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.dto.TipologiaDTO;
import it.popso.popsogift.entity.Tipologia;
import org.springframework.stereotype.Component;

@Component
public class TipologiaMapper {

    public Tipologia getTipologia(CampagnaDTO campagnaDTO){
        TipologiaDTO tipologiaDTO = campagnaDTO.getTipologia();
        Tipologia tipologia = new Tipologia();
        tipologia.setIdTipologia(tipologiaDTO.getIdTipologia());
        tipologia.setNomeTipologia(TipologiaDTO.fromIdTipologia(tipologia.getIdTipologia()));
        return tipologia;
    }
}
