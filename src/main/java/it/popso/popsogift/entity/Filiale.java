package it.popso.popsogift.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="FILIALE")
@Getter
@Setter
public class Filiale {
    @Id
    @Column(name="CODICE_FILIALE")
    private String codiceFiliale;

    @OneToMany(mappedBy="filiale")
    private List<Beneficiario> listaBeneficiari;

}
