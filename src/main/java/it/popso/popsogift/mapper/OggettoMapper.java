package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.CategoriaDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Categoria;
import it.popso.popsogift.entity.Oggetto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OggettoMapper {

    OggettoDTO oggettoToOggettoDTO(Oggetto oggetto);

    Oggetto oggettoDTOToOggetto(OggettoDTO oggettoDTO);

    List<Oggetto> listaOggettiDTOToEntity(List<OggettoDTO> oggettiDTO);
    List<OggettoDTO> listaOggettiToDTO(List<Oggetto> oggetti);

    default Categoria toCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaDTO.getIdCategoria());
        categoria.setNomeCategoria(categoriaDTO);
        return categoria;
    }

    default CategoriaDTO mapCategoriaDTOtoCategoria(Categoria categoria){
        return categoria.getNomeCategoria();
    }

}
