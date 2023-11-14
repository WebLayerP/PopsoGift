package it.popso.popsogift.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="FILIALE")
@Data
public class Filiale {
    @Id
    @Column(name="CODICE_FILIALE")
    private String codiceFiliale;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_BENEFICIARIO_FILIALE",
            joinColumns = @JoinColumn(name = "CODICE_FILIALE"),
            inverseJoinColumns = @JoinColumn(name = "NDG"))
    private List<Beneficiario> listaBeneficiari;

}
