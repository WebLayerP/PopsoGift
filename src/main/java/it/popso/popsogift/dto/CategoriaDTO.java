package it.popso.popsogift.dto;

import java.io.Serializable;
import java.util.Objects;

public enum CategoriaDTO implements Serializable {

    CATEGORIA1(1),  //TODO riguardare le categorie
    CATEGORIA2(2),
    CATEGORIA3(3);

    private Integer idCategoria;

    CategoriaDTO(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }


    public static CategoriaDTO fromIdCategoria(Integer idCategoria) {
        for (CategoriaDTO categoriaDTO : CategoriaDTO.values()) {
            if (Objects.equals(categoriaDTO.getIdCategoria(), idCategoria)) {
                return categoriaDTO;
            }
        }
        throw new IllegalArgumentException("Categoria non valida: " + idCategoria);
    }
}
