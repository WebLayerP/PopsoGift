package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.TagDTO;
import it.popso.popsogift.dto.TagOutputDTO;
import it.popso.popsogift.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TagMapper {

    TagDTO tagToTagDTO(Tag tag);

    TagOutputDTO tagToTagOutputDTO(Tag tag);

    Tag tagDTOToTag(TagDTO tagDTO);

}
