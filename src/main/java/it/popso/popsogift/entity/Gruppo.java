package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="GRUPPO")
@Data
public class Gruppo {
    @Id
    @Column(name="ID_GRUPPO")
    private Integer idGruppo;
    @Column(name="NOME")
    private String nome;
    @Column(name="DESCRIZIONE")
    private String descrizione;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_BENEFICIARIO_GRUPPO",
            joinColumns = @JoinColumn(name = "ID_GRUPPO"),
            inverseJoinColumns = @JoinColumn(name = "NDG"))
    private List<Beneficiario> listaBeneficiari;
}
