package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Oggetto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface OggettoMapper {

    @Mapping(target = "idOggetto", source = "idOggetto")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "prezzo", source = "prezzo")
    @Mapping(target = "descrizione", source = "descrizione")
    @Mapping(target = "codice", source = "codice")
    OggettoDTO oggettoToOggettoDTO(Oggetto oggetto);

    @Mapping(target = "idOggetto", source = "idOggetto")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "prezzo", source = "prezzo")
    @Mapping(target = "descrizione", source = "descrizione")
    @Mapping(target = "codice", source = "codice")
    Oggetto oggettoDTOToOggetto(OggettoDTO oggettoDTO);
}
