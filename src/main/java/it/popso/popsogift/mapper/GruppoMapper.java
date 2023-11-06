package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.GruppoDTO;
import it.popso.popsogift.entity.Gruppo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GruppoMapper {
    @Mapping(target="listaBeneficiari", ignore = true)
    GruppoDTO gruppoToGruppoDTO(Gruppo gruppo);

    @Mapping(target="listaBeneficiari", ignore = true)
    Gruppo gruppoDTOToGruppo(GruppoDTO gruppoDTO);
}
