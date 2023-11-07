package it.popso.popsogift.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name ="FORNITORE")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fornitore {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="FORNITORE_SEQ")
    @SequenceGenerator(name = "FORNITORE_SEQ", sequenceName = "FORNITORE_SEQ",allocationSize = 1)
    @Column(name="ID_FORNITORE")
    private Integer idFornitore;
    @Column(name="RAGIONE_SOCIALE")
    private String ragioneSociale;
    @Column(name="EMAIL")
    private String email;
    @Column(name="INDIRIZZO")
    private String indirizzo;
    @Column(name="STATO")
    private String stato;
    @Column(name="CAP")
    private String cap;
    @Column(name="TELEFONO")
    private String telefono;
    @Column(name="CITTA")
    private String citta;
    @Column(name="PROVINCIA")
    private String provincia;
    @Column(name="PIVA")
    private String partitaIva;
    @Column(name="DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;
    @Column(name="DATA_INSERIMENTO")
    private Date dataInserimento;
}
