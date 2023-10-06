package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name="BENEFICIARIO")
@Data
public class Beneficiario {
    @Id
    @Column(name="NDG")
    private String ndg;
    @Column(name="DATA_INSERIMENTO")
    private Date dataInserimento;
    @Column(name="NOTE")
    private String note;
    @ManyToOne
    @JoinColumn(name="STATO")
    private StatoBeneficiario statoBeneficiario;
    @ManyToOne
    @JoinColumn(name="CODICE_FILIALE")
    private Filiale filiale;
    @Column(name="DATA_ESTINZIONE")
    private Date dataEstinzione;
    @Column(name="MOTIVO_ESTINZIONE")
    private String motivoEstinzione;
}
