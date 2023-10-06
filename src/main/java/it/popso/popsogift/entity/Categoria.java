package it.popso.popsogift.entity;

import it.popso.popsogift.dto.CategoriaDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="CATEGORIA")
@Data
public class Categoria {
    @Id
    @Column(name="ID_CATEGORIA")
    private Integer idCategoria;
    @Enumerated(EnumType.STRING)
    @Column(name="DESCRIZIONE")
    private CategoriaDTO nomeCategoria;
}
