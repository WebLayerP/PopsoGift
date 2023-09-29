package it.popso.popsogift.service;

import it.popso.popsogift.entity.Tag;
import it.popso.popsogift.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }
}

