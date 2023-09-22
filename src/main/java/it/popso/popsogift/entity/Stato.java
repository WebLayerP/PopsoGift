package it.popso.popsogift.entity;

import it.popso.popsogift.dto.StatoDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="Stato")
@Data
public class Stato {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="STATO_SEQ")
    @SequenceGenerator(name = "STATO_SEQ", sequenceName = "STATO_SEQ",allocationSize = 1)
    private Integer idStato;
    @Enumerated(EnumType.STRING)
    private StatoDTO nomeStato;
}
