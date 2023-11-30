package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.dto.StatoBeneficiarioDTO;
import it.popso.popsogift.entity.Filiale;
import it.popso.popsogift.entity.StatoBeneficiario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FilialeMapper {

    @Mapping(target="listaBeneficiari.statoBeneficiario", ignore = true)
    FilialeDTO filialeToFilialeDTO(Filiale filiale);
    @Mapping(target="listaBeneficiari.statoBeneficiario", ignore = true)
    Filiale filialeDTOToFiliale(FilialeDTO filialeDTO);
    List<Filiale> listaFilialeDTOToEntity(List<FilialeDTO> filialeDTO);
    List<FilialeDTO> listaFilialeToDTO(List<Filiale> filiale);

    default StatoBeneficiario toStatoBeneficiario(StatoBeneficiarioDTO statoBeneficiarioDTO){
        StatoBeneficiario statoBeneficiario = new StatoBeneficiario();
        statoBeneficiario.setIdStato(statoBeneficiarioDTO.getIdStato());
        statoBeneficiario.setNomeStato(statoBeneficiarioDTO);
        return statoBeneficiario;
    }

    default StatoBeneficiarioDTO mapStatoBeneficiarioDTOtoStatoBeneficiario(StatoBeneficiario statoBeneficiario){
        return statoBeneficiario.getNomeStato();
    }
}
