package it.popso.popsogift.service;

import it.popso.popsogift.dto.BeneficiarioDTO;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.BeneficiarioMapper;
import it.popso.popsogift.mapper.StatoBeneficiarioMapper;
import it.popso.popsogift.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiarioService {

    @Autowired
    private final BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private BeneficiarioMapper beneficiarioMapper;

    @Autowired
    private StatoBeneficiarioMapper statoBeneficiarioMapper;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public Beneficiario saveBeneficiario(BeneficiarioDTO beneficiarioDTO){
        Beneficiario beneficiario = beneficiarioMapper.beneficiarioDTOToBeneficiario(beneficiarioDTO);
        beneficiario.setStatoBeneficiario(statoBeneficiarioMapper.getStatoBeneficiario(beneficiarioDTO));
        Beneficiario beneficiarioInserito = null;
        try {
            beneficiarioInserito = beneficiarioRepository.save(beneficiario);
        } catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return beneficiarioInserito;
    }

    public List<Beneficiario> getAllBeneficiario() {
        try {
            return beneficiarioRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
}

