package it.popso.popsogift.entity;

import it.popso.popsogift.dto.BeneficiarioDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="FILIALE")
@Data
public class Filiale {
    @Id
    @Column(name="CODICE_FILIALE")
    private String codiceFiliale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_CAMPAGNA")
    private Campagna campagna;
    @Column(name="NOME_FILIALE")
    private String nomeFiliale;
    @Column(name="INDIRIZZO")
    private String indirizzo;
    //private List<BeneficiarioDTO> listaBeneficiari;   TODO collegare e gestire correttamente
}
