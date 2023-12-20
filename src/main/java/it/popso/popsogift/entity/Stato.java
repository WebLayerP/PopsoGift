package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="STATO_CAMPAGNA")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stato {
    @Id
    @Column(name="ID_STATO")
    private Integer idStato;
    @Column(name="DESCRIZIONE")
    private String nomeStato;
}
