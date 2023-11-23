package it.popso.popsogift.service;

import it.popso.popsogift.dto.TagDTO;
import it.popso.popsogift.dto.TagOutputDTO;
import it.popso.popsogift.entity.Tag;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.TagMapper;
import it.popso.popsogift.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagMapper tagMapper;

    public List<Tag> getAllTag() {
        try{
            return tagRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
    public Tag saveTag(TagDTO tagDTO, String matricola){
        Tag tag = tagMapper.tagDTOToTag(tagDTO);
        try {
            tag.setIdTag(null);
            tag.setDataInserimento(new Date());
            tag.setCreatoDa(matricola);
            tag = tagRepository.save(tag);
        } catch(DataIntegrityViolationException e){
            throw new ApplicationFaultMsgException(e.getMessage());
        }
        return tag;
    }

    public TagDTO updateTag(TagDTO tagDTO, int id){
        Tag tag = tagMapper.tagDTOToTag(tagDTO);
        try {
            Tag tagSaved = tagRepository.findById(id).orElseThrow(() -> new ApplicationFaultMsgException("Nessun tag trovato corrispondente all'id ricercato"));
            tag.setIdTag(id);
            tag.setDataInserimento(tagSaved.getDataInserimento());
            tag.setCreatoDa(tagSaved.getCreatoDa());
            tag.setDataAggiornamento(new Date());
            tagRepository.save(tag);
        } catch(DataIntegrityViolationException e){
            throw new ApplicationFaultMsgException(e.getMessage());
        }
        return tagMapper.tagToTagDTO(tag);
    }

    public TagOutputDTO findTagById(int id){
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ApplicationFaultMsgException("Nessun tag trovato corrispondente all'id ricercato"));
        TagOutputDTO result = tagMapper.tagToTagOutputDTO(tag);
        result.setNumeroBeneficiari(tagRepository.findNumeroBeneficiari(id));
        result.setNumeroOggetti(tagRepository.findNumeroOmaggi(id));
        return result ;
    }
}

