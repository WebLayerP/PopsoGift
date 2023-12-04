package it.popso.popsogift.service;

import it.popso.popsogift.dto.BeneficiarioDTO;
import it.popso.popsogift.dto.BeneficiarioDettaglioDTO;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.*;
import it.popso.popsogift.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private TagMapper tagMapper;

    @Autowired
    private OggettoMapper oggettoMapper;

    @Autowired
    private FilialeMapper filialeMapper;

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
                setStatoBeneficiarioAndListaGruppi(b, beneficiarioDTO);
                listaBeneficiariDTO.add(beneficiarioDTO);
            }
            return listaBeneficiariDTO;
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
    public BeneficiarioDettaglioDTO getBeneficiarioByNdg(String ndg) {
        Beneficiario beneficiario = beneficiarioRepository.findByNdgAndStatoCancellazione(ndg, Boolean.FALSE);
        if(Objects.isNull(beneficiario)){
            throw new ApplicationFaultMsgException("Il beneficiario con ndg " + ndg + " non è stato trovato");
        }
        BeneficiarioDTO beneficiarioDTO = beneficiarioMapper.beneficiarioToBeneficiarioDTO(beneficiario);
        BeneficiarioDettaglioDTO beneficiarioDettaglioDTO = beneficiarioMapper.beneficiarioDTOToBeneficiarioDettaglioDTO(beneficiarioDTO);
        beneficiarioDettaglioDTO.setStatoBeneficiario(statoBeneficiarioMapper.getStatoBeneficiario(beneficiario));
        beneficiarioDettaglioDTO.setListaGruppi(gruppoMapper.lgruppoToGruppoDTO(beneficiario.getListaGruppi()));

        return beneficiarioMapper.beneficiarioDettaglioCompleto(beneficiarioDettaglioDTO);
    }
    public List<BeneficiarioDettaglioDTO> getListaBeneficiari() {
        List<Beneficiario> listaBeneficiari = beneficiarioRepository.findAll();
        List<BeneficiarioDettaglioDTO> beneficiariDettaglioDTO = new ArrayList<>();
        for(Beneficiario b: listaBeneficiari){
            beneficiariDettaglioDTO.add(getBeneficiarioByNdg(b.getNdg()));
        }
        return beneficiariDettaglioDTO;
    }

    private void setStatoBeneficiarioAndListaGruppi(Beneficiario beneficiario, BeneficiarioDTO beneficiarioDTO) {
        beneficiarioDTO.setStatoBeneficiario(statoBeneficiarioMapper.getStatoBeneficiario(beneficiario));
        beneficiarioDTO.setListaGruppi(gruppoMapper.lgruppoToGruppoDTO(beneficiario.getListaGruppi()));
    }

    public void updateBeneficiario (String ndg, BeneficiarioDettaglioDTO beneficiarioDettaglioDTO){
        BeneficiarioDTO beneficiarioByNdg = beneficiarioMapper.beneficiarioDettaglioDTOToBeneficiarioDTO(getBeneficiarioByNdg(ndg));
        if( beneficiarioByNdg != null) {
            beneficiarioDettaglioDTO.setNdg(ndg);
            beneficiarioDettaglioDTO.setDataInserimento(beneficiarioDettaglioDTO.getDataInserimento());
            beneficiarioDettaglioDTO.setDataAggiornamento(new Date());
            Beneficiario beneficiario = beneficiarioMapper.beneficiarioDettaglioDTOToBeneficiario(beneficiarioDettaglioDTO);
            beneficiario.setListaGruppi(gruppoMapper.lgruppoDTOToGruppo(beneficiarioDettaglioDTO.getListaGruppi()));
            beneficiario.setStatoBeneficiario(statoBeneficiarioMapper.getStatoBeneficiarioD(beneficiarioDettaglioDTO));
            beneficiarioRepository.save(beneficiario);
        }
        else{
            throw new ApplicationFaultMsgException("Errore modifica beneficiario");
        }
    }
}

