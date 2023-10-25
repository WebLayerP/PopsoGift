package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="TAG")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag {

    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="TAG_SEQ")
    @SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ",allocationSize = 1)
    @Column(name="ID_TAG")
    private Integer idTag;
    @Column(name="NOME")
    private String nomeTag;
    @Column(name="DESCRIZIONE")
    private String descrizione;
    @Column(name="DATA_CREAZIONE")
    private Date dataInserimento;
    @Column(name="AUTORE")
    private String creatoDa;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_OGGETTO_TAG",
            joinColumns = @JoinColumn(name = "ID_TAG"),
            inverseJoinColumns = @JoinColumn(name = "ID_OGGETTO"))
    private List<Oggetto> listaOggetti;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "REL_BENEFICIARIO_TAG",
            joinColumns = @JoinColumn(name = "ID_TAG"),
            inverseJoinColumns = @JoinColumn(name = "NDG"))
    private List<Beneficiario> listaBeneficiari;
    @Column(name="DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;
}
