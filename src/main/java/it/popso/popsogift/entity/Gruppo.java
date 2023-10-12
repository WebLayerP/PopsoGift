package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="idGruppo")
@Table(name="GRUPPO")
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
            joinColumns = @JoinColumn(name = "GRUPPO"),
            inverseJoinColumns = @JoinColumn(name = "NDG"))
    private List<Beneficiario> listaBeneficiari;

    public Integer getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(Integer idGruppo) {
        this.idGruppo = idGruppo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Beneficiario> getListaBeneficiari() {
        return listaBeneficiari;
    }

    public void setListaBeneficiari(List<Beneficiario> listaBeneficiari) {
        this.listaBeneficiari = listaBeneficiari;
    }
}
