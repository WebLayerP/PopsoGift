package it.popso.popsogift.entity;

import it.popso.popsogift.dto.StatoBeneficiarioDTO;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name ="STATO_BENEFICIARIO")
@Data
public class StatoBeneficiario {

    @Id
    @Column(name="ID_STATO")
    private Integer idStato;
    @Enumerated(EnumType.STRING)
    @Column(name="DESCRIZIONE")
    private StatoBeneficiarioDTO nomeStato;
}

