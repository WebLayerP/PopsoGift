package it.popso.popsogift.mapper;

import it.popso.popsogift.entity.Stato;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StatoMapper {

    default Stato toStato (Integer idStato){
        Stato stato = new Stato();
        stato.setIdStato(idStato);
        return stato;
    }
}
