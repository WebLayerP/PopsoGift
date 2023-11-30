package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.StatoBeneficiarioDTO;
import it.popso.popsogift.entity.StatoBeneficiario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatoBeneficiarioMapper {

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
