package it.popso.popsogift.utils;

import it.popso.popsogift.dto.CategoriaDTO;
import it.popso.popsogift.entity.Categoria;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.entity.TipologiaOggetto;
import it.popso.popsogift.repository.CategoriaRepository;
import it.popso.popsogift.repository.FornitoreRepository;
import it.popso.popsogift.repository.OggettoRepository;
import it.popso.popsogift.repository.TipologiaOggettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class OggettoDataInitializer {

    @Autowired
    private TipologiaOggettoRepository tipologiaOggettoRepository;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private OggettoRepository oggettoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

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
