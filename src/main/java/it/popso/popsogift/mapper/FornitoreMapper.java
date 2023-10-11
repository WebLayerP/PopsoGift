package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.entity.Oggetto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FornitoreMapper {

    FornitoreDTO fornitoreToFornitoreDTO(Oggetto oggetto);

    Fornitore fornitoreDTOToFornitore(OggettoDTO oggettoDTO);
}
