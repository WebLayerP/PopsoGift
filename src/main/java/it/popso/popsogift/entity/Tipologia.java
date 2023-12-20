package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="TIPOLOGIA_CAMPAGNA")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tipologia {
    @Id
    @Column(name="ID_TIPOLOGIA")
    private Integer idTipologia;
    @Column(name="DESCRIZIONE")
    private String nomeTipologia;
}
