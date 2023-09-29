package it.popso.popsogift.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="TAG")
@Data
public class Tag {

    @Id
    @Column(name="ID_TAG")
    private Integer idTag;
    @Column(name="NOME_TAG")
    private String nomeTag;
    @Column(name="NUMERO_BENEFICIARI")
    private Integer numeroBeneficiari;
    @Column(name="NUMERO_OGGETTI")
    private Integer numeroOggetti;
    @Column(name="DATA_INSERIMENTO")
    private Date dataInserimento;
    @Column(name="CREATO_DA")
    private String creatoDa;

}
