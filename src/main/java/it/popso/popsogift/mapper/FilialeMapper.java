package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.entity.Filiale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FilialeMapper {

    @Mapping(target="listaBeneficiari", ignore = true)
    FilialeDTO filialeToFilialeDTO(Filiale filiale);
    @Mapping(target="listaBeneficiari", ignore = true)
    Filiale filialeDTOToFiliale(FilialeDTO filialeDTO);
    @Mapping(target="listaBeneficiari", ignore = true)
    List<Filiale> listaFilialeDTOToEntity(List<FilialeDTO> filialeDTO);
    @Mapping(target="listaBeneficiari", ignore = true)
    List<FilialeDTO> listaFilialeToDTO(List<Filiale> filiale);
}
