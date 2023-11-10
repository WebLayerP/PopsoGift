package it.popso.popsogift.utils;

import it.popso.popsogift.dto.CategoriaDTO;
import it.popso.popsogift.dto.StatoBeneficiarioDTO;
import it.popso.popsogift.dto.StatoDTO;
import it.popso.popsogift.dto.TipologiaDTO;
import it.popso.popsogift.entity.*;
import it.popso.popsogift.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OggettoDataInitializer {
    @Autowired
    private CampagnaRepository campagnaRepository;
    @Autowired
    private StatoRepository statoRepository;
    @Autowired
    private FilialeRepository filialeRepository;
    @Autowired
    private TipologiaRepository tipologiaRepository;
    @Autowired
    private StatoBeneficiarioRepository statoBeneficiarioRepository;
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    @Autowired
    private TipologiaOggettoRepository tipologiaOggettoRepository;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private OggettoRepository oggettoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void initializeCampagnaData() {

        Stato stato = new Stato();
        stato.setIdStato(1);
        stato.setNomeStato(StatoDTO.IN_CORSO);
        statoRepository.save(stato);

        Tipologia tipologia = new Tipologia();
        tipologia.setIdTipologia(1);
        tipologia.setNomeTipologia(TipologiaDTO.FISICA);
        tipologiaRepository.save(tipologia);

        Filiale filiale = new Filiale();
        filiale.setCodiceFiliale("F0001");
        filialeRepository.save(filiale);
        List<Filiale> listaFiliali = new ArrayList<>();
        listaFiliali.add(filiale);

        StatoBeneficiario statoBeneficiario = new StatoBeneficiario();
        statoBeneficiario.setIdStato(1);
        statoBeneficiario.setNomeStato(StatoBeneficiarioDTO.OK);
        statoBeneficiarioRepository.save(statoBeneficiario);

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNdg("12345");
        beneficiario.setDataInserimento(new Date());
        beneficiario.setStatoBeneficiario(statoBeneficiario);
        beneficiario.setFiliale(filiale);
        beneficiarioRepository.save(beneficiario);

        Campagna campagna = new Campagna();
        campagna
                .setDataAggiornamento(Date.from(LocalDate.of(2023, 10, 2).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        campagna
                .setDataFineModifiche(Date.from(LocalDate.of(2023, 11, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        campagna
                .setDataInizioModifiche(Date.from(LocalDate.of(2023, 10, 3).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        campagna.setDataInserimento(new Date());
        campagna.setDataFine(Date.from(LocalDate.of(2023, 11, 25).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        campagna.setIdCampagna(1);
        campagna.setListaFiliali(listaFiliali);
        campagna.setListaOmaggi(new ArrayList<>());
        campagna.setMatricola("Matricola");
        campagna.setSegnalazione(new ArrayList<>());
        campagna.setStato(stato);
        campagna.setTipologia(tipologia);
        campagna.setTitoloCampagna("Titolo Campagna");
        campagnaRepository.save(campagna);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setDataInserimento(new Date());
        segnalazione.setAutore("Test");
        segnalazione.setDescrizione("Segnalazione di test overview campagna");
        segnalazione.setId(1);
        segnalazione.setBeneficiario(beneficiario);
        segnalazione.setCampagna(campagna);
        segnalazioneRepository.save(segnalazione);

        Segnalazione segnalazione1 = new Segnalazione();
        segnalazione1.setDataInserimento(new Date());
        segnalazione1.setAutore("Test");
        segnalazione1.setDescrizione("Segnalazione  2 di test overview campagna");
        segnalazione1.setId(2);
        segnalazione1.setBeneficiario(beneficiario);
        segnalazione1.setCampagna(campagna);
        segnalazioneRepository.save(segnalazione1);
    }
    public void initializeOggettoData(){
        Oggetto oggetto = new Oggetto();
        TipologiaOggetto tipologia = new TipologiaOggetto();
        tipologia.setIdTipologia(1);
        tipologia.setNomeTipologia("FISICA");
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setNomeCategoria(CategoriaDTO.CATEGORIA1);
        Fornitore fornitore = new Fornitore();
        fornitore.setCap("34552");
        fornitore.setDataInserimento(new Date());
        fornitore.setIdFornitore(1);
        fornitore.setIndirizzo("via");
        fornitore.setEmail("test@test.it");
        fornitore.setCitta("citta");
        fornitore.setStato("Italia");
        fornitore.setProvincia("pro");
        fornitore.setTelefono("65464");
        fornitore.setPartitaIva("44333");
        fornitore.setRagioneSociale("ragione");
        fornitore.setStatoCancellazione(Boolean.FALSE);
        tipologiaOggettoRepository.save(tipologia);
        categoriaRepository.save(categoria);
        fornitoreRepository.save(fornitore);
        oggetto.setFornitore(fornitore);
        oggetto.setIdOggetto(1);
        oggetto.setDescrizione("descr");
        oggetto.setDataInserimento(new Date());
        oggetto.setTipologiaOggetto(tipologia);
        oggetto.setCodice("46456");
        oggetto.setCategoria(categoria);
        oggetto.setNome("nome");
        oggetto.setPrezzo("645");
        oggetto.setDataAggiornamento(Date.from(LocalDate.of(2023, 10, 2).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        oggettoRepository.save(oggetto);
        Oggetto oggettoTwo = new Oggetto();
        TipologiaOggetto tipologiaTwo = new TipologiaOggetto();
        tipologiaTwo.setIdTipologia(2);
        tipologiaTwo.setNomeTipologia("DIGITALE");
        Categoria categoriaTwo = new Categoria();
        categoriaTwo.setIdCategoria(1);
        categoria.setNomeCategoria(CategoriaDTO.CATEGORIA1);
        Fornitore fornitoreTwo = new Fornitore();
        fornitoreTwo.setCap("34552");
        fornitoreTwo.setDataInserimento(new Date());
        fornitoreTwo.setIdFornitore(2);
        fornitoreTwo.setIndirizzo("via");
        fornitoreTwo.setEmail("test@test.it");
        fornitoreTwo.setCitta("citta");
        fornitoreTwo.setStato("Italia");
        fornitoreTwo.setProvincia("pro");
        fornitoreTwo.setTelefono("65464");
        fornitoreTwo.setPartitaIva("44333");
        fornitoreTwo.setRagioneSociale("ragione");
        fornitoreTwo.setStatoCancellazione(Boolean.FALSE);
        tipologiaOggettoRepository.save(tipologiaTwo);
        categoriaRepository.save(categoriaTwo);
        fornitoreRepository.save(fornitoreTwo);
        oggettoTwo.setFornitore(fornitoreTwo);
        oggettoTwo.setIdOggetto(2);
        oggettoTwo.setDescrizione("descr");
        oggettoTwo.setDataInserimento(new Date());
        oggettoTwo.setTipologiaOggetto(tipologiaTwo);
        oggettoTwo.setCodice("46456");
        oggettoTwo.setCategoria(categoriaTwo);
        oggettoTwo.setNome("nome");
        oggettoTwo.setPrezzo("645");
        oggettoTwo.setDataAggiornamento(Date.from(LocalDate.of(2023, 10, 2).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        oggettoRepository.save(oggettoTwo);
    }
}
