package it.popso.popsogift.entity;

import it.popso.popsogift.dto.TipologiaDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="Tipologia")
@Data
public class Tipologia {
    @Id
    @Column(name="IDTIPOLOGIA")
    private Integer idTipologia;
    @Enumerated(EnumType.STRING)
    @Column(name="NOMETIPOLOGIA")
    private TipologiaDTO nomeTipologia;
}
