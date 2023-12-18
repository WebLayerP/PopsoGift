package it.popso.popsogift.service;

import it.popso.popsogift.dto.*;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.*;
import it.popso.popsogift.repository.BeneficiarioRepository;
import it.popso.popsogift.repository.OggettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static it.popso.popsogift.utils.CastObjectsUtils.castObjectIntValue;

@Service
public class BeneficiarioService {

    @Autowired
    private final BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private OggettoRepository oggettoRepository;

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
    public List<BeneficiarioDetailDTO> getListaBeneficiariDetail(Integer idCampagna) {
        List<Beneficiario> listaBeneficiari = beneficiarioRepository.findAll();
        List<BeneficiarioDettaglioDTO> beneficiariDettaglioDTO = new ArrayList<>();
        for(Beneficiario b: listaBeneficiari){
            BeneficiarioDettaglioDTO beneficiarioDettaglioDTO = getBeneficiarioByNdg(b.getNdg());
            List<Object[]> oggettiAssegnati = beneficiarioRepository.findByIdCampagnaAndNdg(idCampagna,b.getNdg());
            List<Integer> listaStringOggetti = new ArrayList<>();
            for(Object[] o : oggettiAssegnati){
               listaStringOggetti.add(castObjectIntValue(o[0]));
            }
            List<Object[]> oggettiStorico = beneficiarioRepository.findByNdgExcludeCampagna(idCampagna,b.getNdg());
            List<Integer> listaStoricoOggetti = new ArrayList<>();
            List<OggettoDTO> listaOggettoDTO = new ArrayList<>();
            for(Object[] o : oggettiStorico){
                Integer idOggetto = castObjectIntValue(o[0]);
                listaStoricoOggetti.add(idOggetto);
                Optional<Oggetto> oggetto = oggettoRepository.findById(idOggetto);
                OggettoDTO oggettoDTO = new OggettoDTO();
                if(oggetto.isPresent()) {
                    oggettoDTO = oggettoMapper.oggettoToOggettoDTO(oggetto.get());
                }
                listaOggettoDTO.add(oggettoDTO);
            }
            beneficiarioDettaglioDTO.setListaOggettiCampagnaBeneficiario(listaStringOggetti);
            beneficiarioDettaglioDTO.setListaOggetti(listaOggettoDTO);
            beneficiariDettaglioDTO.add(beneficiarioDettaglioDTO);
        }

        return getBeneficiariDetailList(beneficiariDettaglioDTO);
    }

    private List<BeneficiarioDetailDTO> getBeneficiariDetailList(List<BeneficiarioDettaglioDTO> beneficiariDettaglioDTO) {
        List<BeneficiarioDetailDTO> beneficiariDetail = new ArrayList<>();
        for(BeneficiarioDettaglioDTO b: beneficiariDettaglioDTO){
            BeneficiarioDetailDTO beneficiarioDetailDTO = new BeneficiarioDetailDTO();
            beneficiarioDetailDTO.setNdg(b.getNdg());
            beneficiarioDetailDTO.setNome(b.getNome());
            beneficiarioDetailDTO.setCognome(b.getCognome());
            List<String> listaGruppiString = new ArrayList<>();
            for(GruppoDTO g: b.getListaGruppi()){
               listaGruppiString.add(g.getNome());
            }
            beneficiarioDetailDTO.setGruppi(listaGruppiString);
            List<String> listaOggettiString = new ArrayList<>();
            for(Integer i: b.getListaOggettiCampagnaBeneficiario()){
            Optional<Oggetto> oggetto = oggettoRepository.findById(i);
            if(oggetto.isPresent()) {
                listaOggettiString.add(oggetto.get().getNome());
            }
            }
            beneficiarioDetailDTO.setOggettiAssegnati(listaOggettiString);
            List<String> listaOggettiStoricoString = new ArrayList<>();
            for(OggettoDTO o: b.getListaOggetti()){
                listaOggettiStoricoString.add(o.getNome());
            }
            beneficiarioDetailDTO.setStoricoOggetti(listaOggettiStoricoString);
            beneficiarioDetailDTO.setGruppi(listaGruppiString);
            beneficiariDetail.add(beneficiarioDetailDTO);
        }
        return beneficiariDetail;
    }

}

