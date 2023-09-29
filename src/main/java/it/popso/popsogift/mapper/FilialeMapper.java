package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.entity.Filiale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FilialeMapper {

    @Mapping(target = "codiceFiliale", source = "codiceFiliale")
    @Mapping(target="nomeFiliale", source="nomeFiliale")
    @Mapping(target="indirizzo", source="indirizzo")
    FilialeDTO filialeToFilialeDTO(Filiale filiale);

    @Mapping(target = "codiceFiliale", source = "codiceFiliale")
    @Mapping(target="nomeFiliale", source="nomeFiliale")
    @Mapping(target="indirizzo", source="indirizzo")
    Filiale filialeDTOToFiliale(FilialeDTO filialeDTO);
}
