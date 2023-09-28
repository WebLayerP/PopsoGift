package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.OmaggioDTO;
import it.popso.popsogift.entity.Omaggio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface OmaggioMapper {

    @Mapping(target = "idOmaggio", source = "idOmaggio")
    OmaggioDTO omaggioToOmaggioDTO(Omaggio omaggio);

    @Mapping(target = "idOmaggio", source = "idOmaggio")
    Omaggio omaggioDTOToOmaggio(OmaggioDTO omaggioDTO);
}
