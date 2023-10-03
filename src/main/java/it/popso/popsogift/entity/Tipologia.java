package it.popso.popsogift.entity;

import it.popso.popsogift.dto.TipologiaDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="TIPOLOGIA_CAMPAGNA")
@Data
public class Tipologia {
    @Id
    @Column(name="ID_TIPOLOGIA")
    private Integer idTipologia;
    @Enumerated(EnumType.STRING)
    @Column(name="DESCRIZIONE")
    private TipologiaDTO nomeTipologia;
}
