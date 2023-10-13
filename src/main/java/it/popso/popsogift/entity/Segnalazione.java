package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="SEGNALAZIONE")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Segnalazione {

    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator="SEGNALAZIONE_SEQ")
    @SequenceGenerator(name = "SEGNALAZIONE_SEQ", sequenceName = "SEGNALAZIONE_SEQ",allocationSize = 1)
    @Column(name="ID")
    private Integer id;
    @Column(name="AUTORE")
    private String autore;
    @Column(name="DESCRIZIONE")
    private String descrizione;
    @ManyToOne
    @JoinColumn(name="ID_CAMPAGNA")
    private Campagna campagna;
    @ManyToOne
    @JoinColumn(name="ID_BENEFICIARIO")
    private Beneficiario beneficiario;
}
