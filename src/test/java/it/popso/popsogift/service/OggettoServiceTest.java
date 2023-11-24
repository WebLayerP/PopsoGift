package it.popso.popsogift.service;

import it.popso.popsogift.PopsogiftApplication;
import it.popso.popsogift.dto.*;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.entity.Tag;
import it.popso.popsogift.entity.TipologiaOggetto;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.mapper.FornitoreMapper;
import it.popso.popsogift.mapper.TagMapper;
import it.popso.popsogift.repository.FornitoreRepository;
import it.popso.popsogift.repository.OggettoRepository;
import it.popso.popsogift.repository.TagRepository;
import it.popso.popsogift.repository.TipologiaOggettoRepository;
import it.popso.popsogift.utils.OggettoDataInitializer;
import org.apache.commons.collections.ArrayStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PopsogiftApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OggettoServiceTest {

    DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Autowired
    private OggettoService oggettoService;

    @Autowired
    private OggettoRepository oggettoRepository;
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TipologiaOggettoRepository tipologiaOggettoRepository;

    @Autowired
    private FornitoreMapper fornitoreMapper;


    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private OggettoDataInitializer oggettoDataInitializer;

    @AfterAll
    public void afterSettings(){
        oggettoRepository.deleteAll();
    }

    @Test
    void inserisciOggettoTest() {
        OggettoDTO oggettoDTO = nuovoOggetto("004", "CAT4", "Oggetto nuovo", "Bicchiere",2,TipologiaOggettoDTO.DIGITALE,23.04);
        OggettoDTO oggettoInserito = oggettoService.saveOggetto(oggettoDTO);
        assertEquals( "CAT4", oggettoInserito.getCategoria());
        assertEquals(formato.format(oggettoInserito.getDataInserimento()), formato.format(new Date()));
    }
    @Test
    void inserisciOggettoTestConTag() {
        OggettoDTO oggettoDTO = nuovoOggetto("004", "CAT4", "Oggetto nuovo", "Bicchiere",2,TipologiaOggettoDTO.DIGITALE,23.04);
        assegnazioneTag(oggettoDTO);
        OggettoDTO oggettoInserito = oggettoService.saveOggetto(oggettoDTO);
        assertEquals( 1, oggettoInserito.getTag().size());
        assertEquals( "Bicchiere", oggettoInserito.getNome());
        assertEquals(formato.format(oggettoInserito.getDataInserimento()), formato.format(new Date()));
    }

    @Test
    void deleteOggettoNonPresente(){
        assertThrows(ApplicationFaultMsgException.class, () -> oggettoService.deleteLogicaOggetto(1934, "34567"));
    }

    private OggettoDTO nuovoOggetto(String codice, String categoria, String descrizione, String nome, Integer numeroColli, TipologiaOggettoDTO tipologia,Double prezzo){
        Fornitore actualFornitoreDTO = new Fornitore();
        actualFornitoreDTO.setCap("Cap");
        actualFornitoreDTO.setCitta("Roma");
        actualFornitoreDTO.setEmail("email@email.it");
        actualFornitoreDTO.setIndirizzo("indirizzo");
        actualFornitoreDTO.setListaOggetti(null);
        actualFornitoreDTO.setPartitaIva("piva");
        actualFornitoreDTO.setProvincia("RM");
        actualFornitoreDTO.setRagioneSociale("ragioneSociale");
        actualFornitoreDTO.setStato("Italia");
        actualFornitoreDTO.setTelefono("0068564789");
        actualFornitoreDTO.setStatoCancellazione(false);
        fornitoreRepository.save(actualFornitoreDTO);
        OggettoDTO actualOggettoDTO = new OggettoDTO();
        actualOggettoDTO.setCodice(codice);
        actualOggettoDTO.setFornitore(fornitoreMapper.fornitoreToDTO(actualFornitoreDTO));
        actualOggettoDTO.setCategoria(categoria);
        actualOggettoDTO.setStatoCancellazione(false);
        actualOggettoDTO.setDescrizione(descrizione);
        actualOggettoDTO.setNome(nome);
        actualOggettoDTO.setNumeroColli(numeroColli);
        actualOggettoDTO.setPrezzo(prezzo);
        TipologiaOggetto tipologiaToSave = new TipologiaOggetto();
        tipologiaToSave.setNomeTipologia(tipologia);
        tipologiaToSave.setIdTipologia(2);
        tipologiaOggettoRepository.save(tipologiaToSave);
        actualOggettoDTO.setTipologia(tipologiaToSave.getNomeTipologia());
        return actualOggettoDTO;
    }
    private void assegnazioneTag(OggettoDTO oggettoDTO){
        Tag tag = new Tag();
        tag.setNomeTag("nome");
        tag.setDataInserimento(new Date());
        tag.setDescrizione("desc");
        tag.setDataAggiornamento(null);
        tag.setCreatoDa("65462");
        tag.setStatoCancellazione(false);
        tagRepository.save(tag);
        List<TagDTO> listaTag = new ArrayList<>();
        listaTag.add(tagMapper.tagToTagDTO(tag));
        oggettoDTO.setTag(listaTag);
    }

}

