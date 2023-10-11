package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.CategoriaDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria getCategoria(OggettoDTO oggettoDTO){
        CategoriaDTO categoriaDTO = oggettoDTO.getCategoria();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaDTO.getIdCategoria());
        categoria.setNomeCategoria(CategoriaDTO.fromIdCategoria(categoria.getIdCategoria()));
        return categoria;
    }
}

