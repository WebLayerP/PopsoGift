package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="TAG")
@Getter
@Setter
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
    @Column(name="DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;
    @Column(name="STATO_CANCELLAZIONE")
    private Boolean statoCancellazione;
    @Column(name="DATA_CANCELLAZIONE")
    private Date dataCancellazione;
    @Column(name="ID_CANCELLAZIONE")
    private String idCancellazione;
}
