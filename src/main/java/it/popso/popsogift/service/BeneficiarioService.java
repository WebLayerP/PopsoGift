package it.popso.popsogift.service;

import it.popso.popsogift.dto.BeneficiarioDTO;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.BeneficiarioMapper;
import it.popso.popsogift.mapper.GruppoMapper;
import it.popso.popsogift.mapper.StatoBeneficiarioMapper;
import it.popso.popsogift.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BeneficiarioService {

    @Autowired
    private final BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private BeneficiarioMapper beneficiarioMapper;

    @Autowired
    private StatoBeneficiarioMapper statoBeneficiarioMapper;

    @Autowired
    private GruppoMapper gruppoMapper;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public BeneficiarioDTO saveBeneficiario(BeneficiarioDTO beneficiarioDTO){
        Beneficiario beneficiario = beneficiarioMapper.beneficiarioDTOToBeneficiario(beneficiarioDTO);
        beneficiario.setListaGruppi(gruppoMapper.lgruppoDTOToGruppo(beneficiarioDTO.getListaGruppi()));
        beneficiario.setStatoBeneficiario(statoBeneficiarioMapper.getStatoBeneficiario(beneficiarioDTO));
        Beneficiario beneficiarioInserito = null;
        try {
            beneficiarioInserito = beneficiarioRepository.save(beneficiario);
        } catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        beneficiarioInserito.setListaGruppi(gruppoMapper.lgruppoDTOToGruppo(beneficiarioDTO.getListaGruppi()));
        BeneficiarioDTO benDTO = beneficiarioMapper.beneficiarioToBeneficiarioDTO(beneficiarioInserito);
        benDTO.setListaGruppi(gruppoMapper.lgruppoToGruppoDTO(beneficiarioInserito.getListaGruppi()));
        return benDTO;
    }

    public List<BeneficiarioDTO> getAllBeneficiario() {
        List<BeneficiarioDTO> listaBeneficiariDTO = new ArrayList<>();
        try {
            List<Beneficiario> listaBeneficiari = beneficiarioRepository.findAll();
            for(Beneficiario b: listaBeneficiari) {
                BeneficiarioDTO beneficiarioDTO = beneficiarioMapper.beneficiarioToBeneficiarioDTO(b);
                beneficiarioDTO.setStatoBeneficiario(statoBeneficiarioMapper.getStatoBeneficiario(b));
                beneficiarioDTO.setListaGruppi(gruppoMapper.lgruppoToGruppoDTO(b.getListaGruppi()));
                listaBeneficiariDTO.add(beneficiarioDTO);
            }
            return listaBeneficiariDTO;
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
    public BeneficiarioDTO getBeneficiarioByNdg(String ndg) {
        Beneficiario beneficiario = beneficiarioRepository.findByNdgAndStatoCancellazione(ndg, Boolean.FALSE);
        if(Objects.isNull(beneficiario)){
            throw new ApplicationFaultMsgException("Il beneficiario con ndg " + ndg + " non è stato trovato");
        }
        return beneficiarioMapper.beneficiarioToBeneficiarioDTO(beneficiario);
    }
}

