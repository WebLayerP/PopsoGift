package it.popso.popsogift.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="FORNITORE")
@Data
public class Fornitore {

    @Id
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
}
