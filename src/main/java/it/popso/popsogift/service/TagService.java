package it.popso.popsogift.service;

import it.popso.popsogift.dto.PaginazioneDTO;
import it.popso.popsogift.dto.TagDTO;
import it.popso.popsogift.dto.TagListDTO;
import it.popso.popsogift.dto.TagOutputDTO;
import it.popso.popsogift.entity.Tag;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.TagMapper;
import it.popso.popsogift.repository.TagRepository;
import it.popso.popsogift.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagMapper tagMapper;

    public TagListDTO getAllTag(int page, int size, String order, String orderBy) {

        TagListDTO result = new TagListDTO();
        try{
            PaginazioneDTO paginazioneDTO = new PaginazioneDTO();
            Pageable pageable;
            if(Constants.ORDER_TYPE_ASC.equals(order))
                pageable = PageRequest.of(page, size, Sort.by(StringUtils.isBlank(orderBy) ? "dataInserimento": orderBy).ascending());
            else
                pageable = PageRequest.of(page, size, Sort.by(StringUtils.isBlank(orderBy) ? "dataInserimento": orderBy).descending());

            Page<Tag> risultati = tagRepository.findByStatoCancellazione(Boolean.FALSE, pageable);

            List<TagOutputDTO> listaResults = tagMapper.toListOutputDto(risultati.getContent());
            listaResults.forEach(this::setNumeroOggettiAndBeneficiari);
            paginazioneDTO.setNumeroPagine(risultati.getTotalPages());
            paginazioneDTO.setNumeroElementiPerPagina(risultati.getSize());
            paginazioneDTO.setNumeroPagina(risultati.getNumber());

            result.setNumeroElementiTotali(risultati.getNumberOfElements());
            result.setPaginazione(paginazioneDTO);
            result.setResults(listaResults);

            return result;
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
    public Tag saveTag(TagDTO tagDTO, String matricola){
        Tag tag = tagMapper.tagDTOToTag(tagDTO);
        try {
            tag.setIdTag(null);
            tag.setNomeTag(tag.getNomeTag().toUpperCase());
            tag.setDataInserimento(new Date());
            tag.setStatoCancellazione(false);
            tag.setCreatoDa(matricola);
            tag = tagRepository.save(tag);
        } catch(DataIntegrityViolationException e){
            throw new ApplicationFaultMsgException(e.getMessage());
        }
        return tag;
    }

    public List<TagDTO> dynamicSearch(String nomeTag){
        List<TagDTO> listaTag = tagMapper.toListTagDto(tagRepository.findByNomeTagContainingAndStatoCancellazione(nomeTag, Boolean.FALSE));
        listaTag.stream().forEach(t-> t.setDescrizione(null));
        return listaTag;
    }

    public TagDTO updateTag(TagDTO tagDTO, int id){
        Tag tag = tagMapper.tagDTOToTag(tagDTO);
        try {
            Tag tagSaved = tagRepository.findById(id).orElseThrow(() -> new ApplicationFaultMsgException("Nessun tag trovato corrispondente all'id ricercato"));
            tag.setIdTag(id);
            tag.setNomeTag(tagDTO.getNomeTag().toUpperCase());
            tag.setDataInserimento(tagSaved.getDataInserimento());
            tag.setStatoCancellazione(tagSaved.getStatoCancellazione());
            tag.setCreatoDa(tagSaved.getCreatoDa());
            tag.setDataAggiornamento(new Date());
            tagRepository.save(tag);
        } catch(DataIntegrityViolationException e){
            throw new ApplicationFaultMsgException(e.getMessage());
        }
        return tagMapper.tagToTagDTO(tag);
    }

    public void deleteLogicaTag(int id, String matricola){
        try {
            Tag tag = tagRepository.findByIdTagAndStatoCancellazione(id, Boolean.valueOf(false)).orElseThrow(() -> new ApplicationFaultMsgException("Tag non presente o in stato cancellato"));
            tag.setDataCancellazione(new Date());
            tag.setIdCancellazione(matricola);
            tag.setStatoCancellazione(true);
            tagRepository.save(tag);
        } catch(Exception e){
            throw new ApplicationFaultMsgException(e.getMessage());
        }
    }

    public TagOutputDTO findTagById(int id){
        Tag tag = tagRepository.findByIdTagAndStatoCancellazione(id, Boolean.valueOf(false)).orElseThrow(() -> new ApplicationFaultMsgException("Nessun tag trovato corrispondente all'id ricercato"));
        TagOutputDTO result = tagMapper.tagToTagOutputDTO(tag);
        setNumeroOggettiAndBeneficiari(result);
        return result ;
    }

    private void setNumeroOggettiAndBeneficiari(TagOutputDTO tag){
        tag.setNumeroBeneficiari(tagRepository.findNumeroBeneficiari(tag.getIdTag()));
        tag.setNumeroOggetti(tagRepository.findNumeroOmaggi(tag.getIdTag()));
    }


}

