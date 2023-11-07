package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.entity.Fornitore;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FornitoreMapper {

    Fornitore fornitoreDTOToFornitore(FornitoreDTO fornitoreDTO);

    FornitoreDTO fornitoreToDTO(Fornitore fornitore);

    List<FornitoreDTO> toListFornitoreDTO(List<Fornitore> fornitore);
}
