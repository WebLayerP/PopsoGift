package it.popso.popsogift.entity;

import it.popso.popsogift.dto.StatoDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="STATO")
@Data
public class Stato {
    @Id
    @Column(name="ID_STATO")
    private Integer idStato;
    @Enumerated(EnumType.STRING)
    @Column(name="NOME_STATO")
    private StatoDTO nomeStato;
}
