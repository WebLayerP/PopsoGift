package it.popso.popsogift.entity;

import it.popso.popsogift.dto.StatoDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="Stato")
@Data
public class Stato {
    @Id
    @Column(name="IDSTATO")
    private Integer idStato;
    @Enumerated(EnumType.STRING)
    @Column(name="NOMESTATO")
    private StatoDTO nomeStato;
}
