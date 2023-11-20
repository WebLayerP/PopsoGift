package it.popso.popsogift.service;

import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.dto.FornitoreListDTO;
import it.popso.popsogift.dto.PaginazioneDTO;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.FornitoreMapper;
import it.popso.popsogift.repository.FornitoreRepository;
import it.popso.popsogift.repository.OggettoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class FornitoreService {

    public static final String DATA_INSERIMENTO = "dataInserimento";
    public static final String ORDER_TYPE_ASC = "ASC";
    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private OggettoRepository oggettoRepository;

    @Autowired
    private FornitoreMapper fornitoreMapper;

    public FornitoreListDTO listaFornitori(int page, int size, String order, String orderBy, String ragioneSociale, String partitaIva) {
        FornitoreListDTO result = new FornitoreListDTO();
        PaginazioneDTO paginazioneDTO = new PaginazioneDTO();
        Pageable pageable;
        if(ORDER_TYPE_ASC.equals(order))
            pageable = PageRequest.of(page, size, Sort.by(StringUtils.isBlank(orderBy) ? DATA_INSERIMENTO: orderBy).ascending());
        else
            pageable = PageRequest.of(page, size, Sort.by(StringUtils.isBlank(orderBy) ? DATA_INSERIMENTO: orderBy).descending());
        Page<Fornitore> risultati = fornitoreRepository.findByRagioneSocialeAndPartitaIva(ragioneSociale, partitaIva, Boolean.FALSE, pageable);

        paginazioneDTO.setNumeroPagine(risultati.getTotalPages());
        paginazioneDTO.setNumeroElementiPerPagina(risultati.getSize());
        paginazioneDTO.setNumeroPagina(risultati.getNumber());

        result.setNumeroElementiTotali(risultati.getNumberOfElements());
        result.setPaginazione(paginazioneDTO);
        result.setResults(fornitoreMapper.toListFornitoreDTO(risultati.getContent()));
        return result;
    }

    public FornitoreDTO saveFornitore(FornitoreDTO fornitoreDTO){
        fornitoreDTO.setIdFornitore(null);
        Fornitore fornitore = fornitoreMapper.fornitoreDTOToFornitore(fornitoreDTO);
        fornitore.setDataInserimento(new Date());
        Fornitore fornitoreInserito;
        try {
            fornitoreInserito = fornitoreRepository.save(fornitore);
            aggiornaOggettoDaListaFornitori(fornitoreInserito);
        } catch(org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return fornitoreMapper.fornitoreToDTO(fornitoreInserito);
    }
    public FornitoreDTO fornitoreById(Integer id) {
        Fornitore fornitore = fornitoreRepository.findByIdFornitoreAndStatoCancellazione(id, Boolean.FALSE);
        if(Objects.isNull(fornitore)){
            throw new ApplicationFaultMsgException("Il fornitore con id " + id + " non è stato trovato");
        }
        return fornitoreMapper.fornitoreToDTO(fornitore);
    }
    public void updateFornitore (Integer id, FornitoreDTO fornitoreDTO){
        FornitoreDTO fornitoreByID = fornitoreById(id);
        if( fornitoreByID != null) {
            fornitoreDTO.setIdFornitore(id);
            fornitoreDTO.setDataInserimento(fornitoreByID.getDataInserimento());
            fornitoreDTO.setDataAggiornamento(new Date());
            Fornitore fornitore = fornitoreRepository.save(fornitoreMapper.fornitoreDTOToFornitore(fornitoreDTO));
            aggiornaOggettoDaListaFornitori(fornitore);
        }
        else{
            throw new ApplicationFaultMsgException("Errore modifica fornitore");
        }
    }

    private void aggiornaOggettoDaListaFornitori(Fornitore fornitoreInserito){
        if (fornitoreInserito!=null && !CollectionUtils.isEmpty(fornitoreInserito.getListaOggetti())){
            fornitoreInserito.getListaOggetti().forEach(oggetto -> {
                Optional<Oggetto> oggettoDaSalvare = oggettoRepository.findById(oggetto.getIdOggetto());
                oggettoDaSalvare.ifPresent(o -> {
                    o.setDataAggiornamento(new Date());
                    o.setFornitore(fornitoreInserito);
                    oggettoRepository.save(o);
                });
            });
        }
    }
    public void deleteLogicaFornitore(Integer id, String matricola){
        try {
            Fornitore fornitore = fornitoreRepository.findByIdFornitoreAndStatoCancellazione(id, Boolean.valueOf(false));
            if (fornitore != null) {
                fornitore.setDataCancellazione(new Date());
                fornitore.setIdCancellazione(matricola);
                fornitore.setStatoCancellazione(true);
                fornitoreRepository.save(fornitore);
            }
            else{
                throw new ApplicationFaultMsgException("Fornitore non presente o in stato cancellato");
            }
        } catch(Exception e){
            throw new ApplicationFaultMsgException(e.getMessage());
        }
    }
    public List<FornitoreDTO> getFornitori(){
        List<FornitoreDTO> fornitori = fornitoreMapper.toListFornitoreDTO(fornitoreRepository.findAll());
        List<FornitoreDTO> listaFornitoriOut= new ArrayList<>();
        for(FornitoreDTO f: fornitori){
            FornitoreDTO fornitoreDTO = new FornitoreDTO();
            fornitoreDTO.setIdFornitore(f.getIdFornitore());
            fornitoreDTO.setRagioneSociale(f.getRagioneSociale());
            listaFornitoriOut.add(fornitoreDTO);
        }
        return listaFornitoriOut;
    }
}
