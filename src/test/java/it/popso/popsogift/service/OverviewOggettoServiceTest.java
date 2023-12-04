package it.popso.popsogift.service;

import it.popso.popsogift.PopsogiftApplication;
import it.popso.popsogift.dto.OggettoOverview;
import it.popso.popsogift.utils.OggettoDataInitializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PopsogiftApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OverviewOggettoServiceTest {
    @Autowired
    private OverviewService overviewService;

    @Autowired
    private OggettoDataInitializer oggettoDataInitializer;


    @BeforeAll
    public void setup() {
        oggettoDataInitializer.initializeOggettoData();
    }

    @AfterAll
    public void removeSetUp() {
        oggettoDataInitializer.removeData();
    }


    @Test
    void getOggettoOverviewTestDate() {
        Date dataUltimaModifica = Date.from(LocalDate.of(2023, 10, 2).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        OggettoOverview oggettoOverview = overviewService.getOggettoOverview();
        assertEquals(formato.format(dataUltimaModifica), formato.format(oggettoOverview.getDataUltimoAggiornamento()));

    }
    @Test
    void getOggettoOverviewTestQuery() {
        OggettoOverview oggettoOverview = overviewService.getOggettoOverview();
        assertEquals(1,oggettoOverview.getNumeroOggettiFisici());
        assertEquals(1,oggettoOverview.getNumeroOggettiDigitali());
    }
    @Test
    void getOggettoOverviewTestQueryNumeroFornitori() {
        OggettoOverview oggettoOverview = overviewService.getOggettoOverview();
        assertEquals(10,oggettoOverview.getNumeroFornitori());
    }

}