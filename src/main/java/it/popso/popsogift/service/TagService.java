package it.popso.popsogift.service;

import it.popso.popsogift.dto.TagDTO;
import it.popso.popsogift.entity.Tag;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.BeneficiarioMapper;
import it.popso.popsogift.mapper.OggettoMapper;
import it.popso.popsogift.mapper.TagMapper;
import it.popso.popsogift.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private final TagRepository tagRepository;
    @Autowired
    private final TagMapper tagMapper;

    @Autowired
    private final OggettoMapper oggettoMapper;

    @Autowired
    private final BeneficiarioMapper beneficiarioMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper, OggettoMapper oggettoMapper, BeneficiarioMapper beneficiarioMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.oggettoMapper = oggettoMapper;
        this.beneficiarioMapper = beneficiarioMapper;
    }

    public List<Tag> getAllTag() {
        try{
            return tagRepository.findAll();
        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
    }
    public Tag saveTag(TagDTO tagDTO){
        Tag tag = tagMapper.tagDTOToTag(tagDTO);
        Tag tagInserito = null;
        try {
            tagInserito = tagRepository.save(tag);
        } catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return tagInserito;
    }
}

