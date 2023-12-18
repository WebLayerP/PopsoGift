package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiarioDettaglioDTO {

    private String ndg;
    private String titolo;
    private String nome;
    private String cognome;
    private String qualifica;
    private String professione;
    private String email;
    private String indirizzo;
    private String citta;
    private String cap;
    private String provincia;
    private String stato;
    private String continente;
    private Date dataInserimento;
    private Date dataAggiornamento;
    private String note;
    private StatoBeneficiarioDTO statoBeneficiario;
    private String motivoEstinzione;
    private List<GruppoDTO> listaGruppi;
    private Boolean privacy;
    private Boolean statoCancellazione;
    private Date dataCancellazione;
    private String idCancellazione;
    private String matricolaProponente;
    private FilialeDTO filiale;
    private List<TagDTO> tag;
    private String ragioneSociale;
    private String indirizzoAz;
    private String capAz;
    private String cittaAz;
    private String provinciaAz;
    private String statoAz;
    private String continenteAz;
    private List<OggettoDTO> listaOggetti;
    @JsonIgnore
    private List<Integer> listaOggettiCampagnaBeneficiario;

}
