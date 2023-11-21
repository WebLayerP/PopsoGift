package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CategoriaDTO implements Serializable {
    private Integer idCategoria;
    private String nomeCategoria;
}
