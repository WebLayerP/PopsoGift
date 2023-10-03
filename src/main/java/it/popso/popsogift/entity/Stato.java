package it.popso.popsogift.entity;

import it.popso.popsogift.dto.StatoDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="STATO_CAMPAGNA")
@Data
public class Stato {
    @Id
    @Column(name="ID_STATO")
    private Integer idStato;
    @Enumerated(EnumType.STRING)
    @Column(name="DESCRIZIONE")
    private StatoDTO nomeStato;
}
