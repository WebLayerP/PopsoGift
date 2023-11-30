package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.BeneficiarioDTO;
import it.popso.popsogift.dto.BeneficiarioDettaglioDTO;
import it.popso.popsogift.dto.StatoBeneficiarioDTO;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.entity.StatoBeneficiario;
import org.springframework.stereotype.Component;

@Component
public class StatoBeneficiarioMapper {

    public StatoBeneficiario getStatoBeneficiario(BeneficiarioDTO beneficiarioDTO) {
        StatoBeneficiarioDTO statoBeneficiarioDTO = beneficiarioDTO.getStatoBeneficiario();
        StatoBeneficiario statoBeneficiario = new StatoBeneficiario();
        statoBeneficiario.setIdStato(statoBeneficiarioDTO.getIdStato());
        statoBeneficiario.setNomeStato(StatoBeneficiarioDTO.fromIdStato(statoBeneficiario.getIdStato()));
        return statoBeneficiario;
    }
    public StatoBeneficiarioDTO getStatoBeneficiario(Beneficiario beneficiario) {
        StatoBeneficiario statoBeneficiario = beneficiario.getStatoBeneficiario();
        return statoBeneficiario.getNomeStato();
    }
    public StatoBeneficiario getStatoBeneficiarioD(BeneficiarioDettaglioDTO beneficiarioDettaglioDTO) {
        StatoBeneficiarioDTO statoBeneficiarioDTO = beneficiarioDettaglioDTO.getStatoBeneficiario();
        StatoBeneficiario statoBeneficiario = new StatoBeneficiario();
        statoBeneficiario.setIdStato(statoBeneficiarioDTO.getIdStato());
        statoBeneficiario.setNomeStato(StatoBeneficiarioDTO.fromIdStato(statoBeneficiario.getIdStato()));
        return statoBeneficiario;
    }
}