package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.NotificaDTO;
import it.popso.popsogift.entity.Notifica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface NotificaMapper {

    @Mapping(target="tipologiaNotifica", source = "tipologiaNotifica.descrizione")
    @Mapping(target="campagna", ignore = true)
    @Mapping(target="filiale", ignore = true)
    @Mapping(target="letta", ignore = true)
    @Mapping(target="ruolo", ignore = true)
    NotificaDTO notificaToNotificaDto(Notifica notifica);

    List<NotificaDTO> listNotificaToListNotificaDto(List<Notifica> listaNotifiche);
}
