package it.popso.popsogift.service;

import it.popso.popsogift.PopsogiftApplication;
import it.popso.popsogift.dto.PanoramicaOverview;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.repository.BeneficiarioRepository;
import it.popso.popsogift.utils.BeneficiarioDataInitializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

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
class BeneficiarioServiceTest {
    @Autowired
    private OverviewService overviewService;

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private BeneficiarioDataInitializer beneficiarioDataInitializer;


    @BeforeAll
    public void setup() {
        beneficiarioDataInitializer.initializeBeneficiarioData();
    }

    @Test
    void getAllBeneficiario() {
        List<Beneficiario> listaBeneficiari = beneficiarioRepository.findAll();
        assertEquals(1,listaBeneficiari.size());
    }

    @Test
    void getBeneficiarioOverviewSize1() {
        PanoramicaOverview listaBeneficiariOverview = overviewService.getAnagraficaOverview();
        assertEquals(1,listaBeneficiariOverview.getNumeroPrivacyKO());
        assertEquals(1,listaBeneficiariOverview.getNumeroBeneficiari());
    }

    @Test
    void getBeneficiarioOverviewSize2() {
        beneficiarioDataInitializer.insertNewBeneficiario();
        PanoramicaOverview listaBeneficiariOverview = overviewService.getAnagraficaOverview();
        assertEquals(2,listaBeneficiariOverview.getNumeroBeneficiari());
        assertEquals(1,listaBeneficiariOverview.getNumeroPrivacyKO());
    }
}