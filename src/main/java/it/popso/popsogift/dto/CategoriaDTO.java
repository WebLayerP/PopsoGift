package it.popso.popsogift.dto;

import java.io.Serializable;

public enum CategoriaDTO implements Serializable {

    CATEGORIA1(1),
    CATEGORIA2(2),
    CATEGORIA3(3);

    private Integer idCategoria;

    CategoriaDTO(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }
}
