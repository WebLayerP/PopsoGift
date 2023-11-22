package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.popso.popsogift.dto.TipologiaOggettoDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="TIPOLOGIA_OGGETTO")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipologiaOggetto {
    @Id
    @Column(name="ID_TIPOLOGIA")
    private Integer idTipologia;
    @Enumerated(EnumType.STRING)
    @Column(name="DESCRIZIONE")
    private TipologiaOggettoDTO nomeTipologia;
}
