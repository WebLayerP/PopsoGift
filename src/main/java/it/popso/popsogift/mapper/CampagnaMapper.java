package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.entity.Campagna;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CampagnaMapper {

    @Mapping(target = "idCampagna", source = "idCampagna")
    @Mapping(target = "titoloCampagna", source = "titoloCampagna")
    @Mapping(target = "dataInizioModifiche", source = "dataInizioModifiche")
    @Mapping(target = "dataFineModifiche", source = "dataFineModifiche")
    @Mapping(target = "listaOmaggi", source = "listaOmaggi")
    @Mapping(target = "listaFiliali", source = "listaFiliali")
    @Mapping(target="tipologia", ignore = true)
    @Mapping(target="stato", ignore = true)
    CampagnaDTO campagnaToCampagnaDTO(Campagna campagna);


    @Mapping(target = "idCampagna", source = "idCampagna")
    @Mapping(target = "titoloCampagna", source = "titoloCampagna")
    @Mapping(target = "dataInizioModifiche", source = "dataInizioModifiche")
    @Mapping(target = "dataFineModifiche", source = "dataFineModifiche")
    @Mapping(target = "listaOmaggi", source = "listaOmaggi")
    @Mapping(target = "listaFiliali", source = "listaFiliali")
    @Mapping(target="tipologia", ignore = true)
    @Mapping(target="stato", ignore = true)
    Campagna campagnaDTOToEntity(CampagnaDTO campagnaDTO);

}

