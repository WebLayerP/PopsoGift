package it.popso.popsogift.mapper;

import it.popso.popsogift.entity.Tipologia;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TipologiaMapper {

    default Tipologia toTipologia(Integer idTipologia){
        Tipologia tipologia = new Tipologia();
        tipologia.setIdTipologia(idTipologia);
        return tipologia;
    }
}
