package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.entity.Campagna;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CampagnaMapper {

    @Mapping(target="tipologia", ignore = true)
    @Mapping(target="stato", ignore = true)
    @Mapping(target="listaOmaggi", ignore = true)
    @Mapping(target="listaFiliali", ignore = true)
    CampagnaDTO campagnaToCampagnaDTO(Campagna campagna);


    @Mapping(target="tipologia", ignore = true)
    @Mapping(target="stato", ignore = true)
    @Mapping(target="listaOmaggi", ignore = true)
    @Mapping(target="listaFiliali", ignore = true)
    Campagna campagnaDTOToEntity(CampagnaDTO campagnaDTO);

}

