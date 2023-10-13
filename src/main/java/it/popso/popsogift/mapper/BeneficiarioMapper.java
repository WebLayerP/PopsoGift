package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.BeneficiarioDTO;
import it.popso.popsogift.entity.Beneficiario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BeneficiarioMapper {
    @Mapping(target="statoBeneficiario", ignore = true)
    BeneficiarioDTO beneficiarioToBeneficiarioDTO(Beneficiario beneficiario);

    @Mapping(target="statoBeneficiario", ignore = true)
    Beneficiario beneficiarioDTOToBeneficiario(BeneficiarioDTO beneficiarioDTO);

}

