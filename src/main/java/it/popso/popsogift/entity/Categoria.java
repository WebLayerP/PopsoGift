package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="CATEGORIA")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoria {
    @Id
    @Column(name="DESCRIZIONE")
    private String nomeCategoria;
}
