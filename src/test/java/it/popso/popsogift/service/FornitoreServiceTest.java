package it.popso.popsogift.service;

import it.popso.popsogift.PopsogiftApplication;
import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.dto.FornitoreListDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import it.popso.popsogift.repository.FornitoreRepository;
import it.popso.popsogift.utils.OggettoDataInitializer;
import org.junit.jupiter.api.*;
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
public class FornitoreServiceTest {

    DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Autowired
    private FornitoreService fornitoreService;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private OggettoDataInitializer oggettoDataInitializer;

    @AfterAll
    public void afterSettings(){
        fornitoreRepository.deleteAll();
    }

    @Test
    void inserisciFornitoreTestNoOggetti() {
        FornitoreDTO fornitoreDTO = nuovoFornitore("fornitore@example.org ", "Via Roma 1", "Fornitore1 Srl", "584309543");
        FornitoreDTO fornitoreInserito = fornitoreService.saveFornitore(fornitoreDTO);
        assertEquals( "Fornitore1 Srl", fornitoreInserito.getRagioneSociale());
        assertEquals(formato.format(fornitoreInserito.getDataInserimento()), formato.format(new Date()));
    }

    @Test
    void inserisciFornitoreTestConOggetto() {
        FornitoreDTO fornitoreDTO = nuovoFornitore("fornitoreTest2@example.org ", "Via Roma 156", "FornitoreTest2 Srl", "4530754302");
        assegnazioneOggetto(fornitoreDTO);
        FornitoreDTO fornitoreInserito = fornitoreService.saveFornitore(fornitoreDTO);
        assertEquals( 1, fornitoreInserito.getListaOggetti().size());
        assertEquals( "FornitoreTest2 Srl", fornitoreInserito.getRagioneSociale());
        assertEquals(formato.format(fornitoreInserito.getDataInserimento()), formato.format(new Date()));
    }


    @Test
    void fornitoreByIdTest() {
        FornitoreDTO fornitoreDTO = nuovoFornitore("fornitoreByIdConOggetto@example.org ", "Via Roma 3", "FornitoreById Srl", "58439543");
        FornitoreDTO saved = fornitoreService.saveFornitore(fornitoreDTO);
        FornitoreDTO fornitoreById = fornitoreService.fornitoreById(saved.getIdFornitore());
        assertEquals( 0, fornitoreById.getListaOggetti().size());
        assertEquals( "FornitoreById Srl", fornitoreById.getRagioneSociale());
    }

    @Test
    void fornitoreByIdTestConOggetto() {
        FornitoreDTO fornitoreDTO = nuovoFornitore("fornitoreById@example.org ", "Via Roma 3", "FornitoreById Srl", "58439543");
        assegnazioneOggetto(fornitoreDTO);
        FornitoreDTO saved = fornitoreService.saveFornitore(fornitoreDTO);
        FornitoreDTO fornitoreById = fornitoreService.fornitoreById(saved.getIdFornitore());
        assertEquals( 1, fornitoreById.getListaOggetti().size());
        assertEquals( "FornitoreById Srl", fornitoreById.getRagioneSociale());
    }

    @Test
    void fornitoreModificaTest() {
        FornitoreDTO fornitoreDTO = nuovoFornitore("fornitoreNonModificato@example.org ", "Via Roma 334", "FornitoreNonModificato Srl", "643757534");
        FornitoreDTO saved = fornitoreService.saveFornitore(fornitoreDTO);
        assertEquals( "FornitoreNonModificato Srl", saved.getRagioneSociale());
        FornitoreDTO fornitoreModificatoDTO = nuovoFornitore("fornitoreModificato@example.org", "Via Roma 4", "FornitoreModificato Srl", "643757534");
        assegnazioneOggetto(fornitoreModificatoDTO);
        fornitoreService.updateFornitore(saved.getIdFornitore(),fornitoreModificatoDTO);
        FornitoreDTO fornitore = fornitoreService.fornitoreById(saved.getIdFornitore());
        assertEquals( 1, fornitore.getListaOggetti().size());
        assertEquals( "fornitoreModificato@example.org", fornitore.getEmail());
        assertEquals(formato.format(fornitore.getDataAggiornamento()), formato.format(new Date()));
    }

    @Test
    void fornitoreModificaTestDatoNonPresente() {
        FornitoreDTO fornitoreModificatoDTO = nuovoFornitore("fModificato@example.org", "Via Roma 456", "FModificato Srl", "75668585");
        assegnazioneOggetto(fornitoreModificatoDTO);
        assertThrows(ApplicationFaultMsgException.class, () -> fornitoreService.updateFornitore(1232,fornitoreModificatoDTO));
    }

    @Test
    void fornitoreListOredByDataInserimentoDesc(){
        salvaFornitoriOrderListTests();
        FornitoreListDTO fornitoriList = fornitoreService.listaFornitori(0,2, null, null, null, null);
        assertEquals("FornitoreB Srl", fornitoriList.getResults().get(0).getRagioneSociale());
        assertEquals("FornitoreA Srl", fornitoriList.getResults().get(1).getRagioneSociale());
    }

    @Test
    void fornitoreListOredByDataInserimentoAsc(){
        salvaFornitoriOrderListTests();
        FornitoreListDTO fornitoriList = fornitoreService.listaFornitori(0,2, "ASC", null, null, null);
        assertEquals("FornitoreA Srl", fornitoriList.getResults().get(0).getRagioneSociale());
        assertEquals("FornitoreB Srl", fornitoriList.getResults().get(1).getRagioneSociale());
    }

    @Test
    void fornitoreListOredByRagioneSocialeDesc(){
        salvaFornitoriOrderListTests();
        FornitoreListDTO fornitoriList = fornitoreService.listaFornitori(0,2, "DESC", "ragioneSociale", null, null);
        assertEquals("FornitoreB Srl", fornitoriList.getResults().get(0).getRagioneSociale());
        assertEquals("FornitoreA Srl", fornitoriList.getResults().get(1).getRagioneSociale());
    }

    @Test
    void fornitoreListOredByPivaAsc(){
        salvaFornitoriOrderListTests();
        FornitoreListDTO fornitoriList = fornitoreService.listaFornitori(0,2, "ASC", "partitaIva", null, null);
        assertEquals("FornitoreB Srl", fornitoriList.getResults().get(0).getRagioneSociale());
        assertEquals("FornitoreA Srl", fornitoriList.getResults().get(1).getRagioneSociale());
    }

    @Test
    void fornitoreListFilterPiva(){
        salvaFornitoriOrderListTests();
        FornitoreListDTO fornitoriList = fornitoreService.listaFornitori(0,2, "ASC", "partitaIva", null, "956750");
        assertEquals("956750", fornitoriList.getResults().get(0).getPartitaIva());
    }

    @Test
    void fornitoreListFilterRagioneSociale(){
        salvaFornitoriOrderListTests();
        FornitoreListDTO fornitoriList = fornitoreService.listaFornitori(0,2, "ASC", "partitaIva", "FornitoreA Srl", null);
        assertEquals("FornitoreA Srl", fornitoriList.getResults().get(0).getRagioneSociale());
    }

    @Test
    void fornitoreListFilterRagioneSocialeAndPiva(){
        salvaFornitoriOrderListTests();
        FornitoreListDTO fornitoriList = fornitoreService.listaFornitori(0,2, "ASC", "partitaIva", "FornitoreA Srl", "956750");
        assertEquals(0, fornitoriList.getResults().size());
    }

    @Test
    void deleteFornitore(){
        FornitoreDTO fornitoreDTO = nuovoFornitore("fornitoreToDelete", "indirizzo to delete", "fornitoreTdl", "pivatdl");
        FornitoreDTO saved = fornitoreService.saveFornitore(fornitoreDTO);
        fornitoreService.deleteLogicaFornitore(saved.getIdFornitore(), "34567");
        Fornitore fornitoreDeleted = fornitoreRepository.findById(saved.getIdFornitore()).get();
        assertEquals("34567", fornitoreDeleted.getIdCancellazione());
        assertEquals(true, fornitoreDeleted.getStatoCancellazione());
        assertEquals(formato.format(new Date()), formato.format(fornitoreDeleted.getDataCancellazione()));

    }

    @Test
    void deleteFornitoreNonPresente(){
        assertThrows(ApplicationFaultMsgException.class, () -> fornitoreService.deleteLogicaFornitore(1934, "34567"));
    }

    private FornitoreDTO nuovoFornitore(String email, String indirizzo, String ragioneSociale, String piva){
        FornitoreDTO actualFornitoreDTO = new FornitoreDTO();
        actualFornitoreDTO.setCap("Cap");
        actualFornitoreDTO.setCitta("Roma");
        actualFornitoreDTO.setEmail(email);
        actualFornitoreDTO.setIndirizzo(indirizzo);
        ArrayList<OggettoDTO> oggettoDTOList = new ArrayList<>();
        actualFornitoreDTO.setListaOggetti(oggettoDTOList);
        actualFornitoreDTO.setPartitaIva(piva);
        actualFornitoreDTO.setProvincia("RM");
        actualFornitoreDTO.setRagioneSociale(ragioneSociale);
        actualFornitoreDTO.setStato("Italia");
        actualFornitoreDTO.setTelefono("0068564789");
        actualFornitoreDTO.setStatoCancellazione(false);
        return actualFornitoreDTO;
    }

    private void assegnazioneOggetto(FornitoreDTO fornitoreDTO){
        oggettoDataInitializer.initializeOggettoData();
        List<OggettoDTO> listaOggetti = new ArrayList<>();
        listaOggetti.add(oggettoDataInitializer.oggettoDTOById());
        fornitoreDTO.setListaOggetti(listaOggetti);
    }

    private void salvaFornitoriOrderListTests(){
        oggettoDataInitializer.removeData();
        fornitoreRepository.deleteAll();
        FornitoreDTO fornitore1 = nuovoFornitore("f1@example.org ", "Via Garibaldi 334", "FornitoreA Srl", "956757");
        FornitoreDTO fornitore2 = nuovoFornitore("f2@example.org ", "Via Cavour 334", "FornitoreB Srl", "956750");
        fornitoreService.saveFornitore(fornitore1);
        fornitoreService.saveFornitore(fornitore2);
    }
}
