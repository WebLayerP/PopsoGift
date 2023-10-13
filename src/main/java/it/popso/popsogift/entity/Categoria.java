package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.popso.popsogift.dto.CategoriaDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="CATEGORIA")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoria {
    @Id
    @Column(name="ID_CATEGORIA")
    private Integer idCategoria;
    @Enumerated(EnumType.STRING)
    @Column(name="DESCRIZIONE")
    private CategoriaDTO nomeCategoria;
}
