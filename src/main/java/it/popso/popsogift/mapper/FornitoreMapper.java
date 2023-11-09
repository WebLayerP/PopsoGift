package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.CategoriaDTO;
import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Categoria;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.entity.Oggetto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FornitoreMapper {

    Fornitore fornitoreDTOToFornitore(FornitoreDTO fornitoreDTO);

    FornitoreDTO fornitoreToDTO(Fornitore fornitore);

    List<FornitoreDTO> toListFornitoreDTO(List<Fornitore> fornitore);

    @Mapping(target="fornitore", ignore = true)
    OggettoDTO mapToOggettoDTO(Oggetto oggetto);

    default CategoriaDTO maptoCategoriaDTO(Categoria categoria){
        return categoria.getNomeCategoria();
    }
}
