package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.OmaggioDTO;
import it.popso.popsogift.entity.Omaggio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface OmaggioMapper {

    @Mapping(target = "idOmaggio", source = "idOmaggio")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "prezzo", source = "prezzo")
    @Mapping(target = "descrizione", source = "descrizione")
    @Mapping(target = "codice", source = "codice")
    OmaggioDTO omaggioToOmaggioDTO(Omaggio omaggio);

    @Mapping(target = "idOmaggio", source = "idOmaggio")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "prezzo", source = "prezzo")
    @Mapping(target = "descrizione", source = "descrizione")
    @Mapping(target = "codice", source = "codice")
    Omaggio omaggioDTOToOmaggio(OmaggioDTO omaggioDTO);
}
