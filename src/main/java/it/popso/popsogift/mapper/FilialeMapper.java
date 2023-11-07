package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.entity.Filiale;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FilialeMapper {

    FilialeDTO filialeToFilialeDTO(Filiale filiale);

    Filiale filialeDTOToFiliale(FilialeDTO filialeDTO);

    List<Filiale> listaFilialeDTOToEntity(List<FilialeDTO> filialeDTO);
    List<FilialeDTO> listaFilialeToDTO(List<Filiale> filiale);
}
