package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="idGruppo")
@Table(name="GRUPPO")
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Column(name="DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;
    @Column(name="DATA_INSERIMENTO")
    private Date dataInserimento;

}
