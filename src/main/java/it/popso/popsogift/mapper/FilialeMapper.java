package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.entity.Filiale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FilialeMapper {

    @Mapping(target = "codiceFiliale", source = "codiceFiliale")
    FilialeDTO filialeToFilialeDTO(Filiale filiale);

    @Mapping(target = "codiceFiliale", source = "codiceFiliale")
    Filiale filialeDTOToFiliale(FilialeDTO filialeDTO);
}
