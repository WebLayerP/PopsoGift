package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Oggetto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OggettoMapper {
    @Mapping(target="categoria", ignore = true)
    OggettoDTO oggettoToOggettoDTO(Oggetto oggetto);
    @Mapping(target="categoria", ignore = true)
    Oggetto oggettoDTOToOggetto(OggettoDTO oggettoDTO);

    List<Oggetto> listaOggettiDTOToEntity(List<OggettoDTO> oggettiDTO);
    List<OggettoDTO> listaOggettiToDTO(List<Oggetto> oggetti);
}
