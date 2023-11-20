package it.popso.popsogift.service;

import it.popso.popsogift.PopsogiftApplication;
import it.popso.popsogift.dto.CampagnaGroup;
import it.popso.popsogift.dto.CampagnaOverview;
import it.popso.popsogift.utils.CampagnaDataInitializer;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PopsogiftApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OverviewCampagnaServiceTest {
    @Autowired
    private OverviewService overviewService;

    @Autowired
    private CampagnaDataInitializer campagnaDataInitializer;

    @BeforeAll
    public void setup() {
        campagnaDataInitializer.initializeCampagnaData();
    }

    @Test
    void testGetCampagneOverviewRE() {
        Date dataUltimaModifica = Date.from(LocalDate.of(2023, 10, 2).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        CampagnaGroup actualCampagneOverview = overviewService.getCampagneOverview(OverviewService.RE, null);
        assertEquals(formato.format(dataUltimaModifica), formato.format(actualCampagneOverview.getDataUltimoAggiornamento()));
    }

    @Test
    void testGetCampagneOverviewRF_conFiliale() {
        Date dataUltimaModifica = Date.from(LocalDate.of(2023, 10, 2).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        List<String> filialiList =  Arrays.asList("F0001");
        CampagnaGroup actualCampagneOverview = overviewService.getCampagneOverview(OverviewService.RF, filialiList);
        assertEquals(formato.format(dataUltimaModifica), formato.format(actualCampagneOverview.getDataUltimoAggiornamento()));
    }

    @Test
    void testGetCampagneOverviewRF_senzaFiliale() {
        Date dataUltimaModifica = Date.from(LocalDate.of(2023, 10, 2).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        List<String> filialiList =  Arrays.asList("F0003");
        CampagnaGroup actualCampagneOverview = overviewService.getCampagneOverview(OverviewService.RF, filialiList);
        assertEquals(0, actualCampagneOverview.getNumeroCampagneInCorso());
        assertEquals(formato.format(dataUltimaModifica), formato.format(actualCampagneOverview.getDataUltimoAggiornamento()));
    }

    @Test
    void testGetCampagnaOverview_senzaOggetti() {
        CampagnaOverview actualCampagneOverview = overviewService.getCampagnaOverview(1);
        assertEquals(0, actualCampagneOverview.getTotCosto());
    }

}

