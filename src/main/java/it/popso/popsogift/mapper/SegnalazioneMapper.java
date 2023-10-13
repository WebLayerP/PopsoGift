package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.SegnalazioneDTO;
import it.popso.popsogift.entity.Segnalazione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SegnalazioneMapper {
    @Mapping(target="beneficiario", ignore = true)
    @Mapping(target="campagna", ignore = true)
    SegnalazioneDTO segnalazioneToSegnalazioneDTO(Segnalazione segnalazione);

    @Mapping(target="beneficiario", ignore = true)
    @Mapping(target="campagna", ignore = true)
    Segnalazione segnalazioneDTOToEntity(SegnalazioneDTO segnalazioneDTO);

}
