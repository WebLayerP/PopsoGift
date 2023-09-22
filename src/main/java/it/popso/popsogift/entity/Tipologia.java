package it.popso.popsogift.entity;

import it.popso.popsogift.dto.TipologiaDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="Tipologia")
@Data
public class Tipologia {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="TIPOLOGIA_SEQ")
    @SequenceGenerator(name = "TIPOLOGIA_SEQ", sequenceName = "TIPOLOGIA_SEQ",allocationSize = 1)
    private Integer idTipologia;
    @Enumerated(EnumType.STRING)
    private TipologiaDTO nomeTipologia;
}
