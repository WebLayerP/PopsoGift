package it.popso.popsogift.controllers;

import it.popso.popsogift.PopsogiftApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PopsogiftApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class BeneficiarioControllerTest {

    @Autowired
    private BeneficiarioController beneficiarioController;


    @Test
    public void getAllBeneficiario_thenStatus200()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        try {
            mockMvc.perform(get("/beneficiario/all")
                            .header("ruolo", "RE")
                            .header("matricola", "12345")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            throw e;
        }
    }

    @Test
    public void overviewOggetto_withoutheader_thenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        try {
            mockMvc.perform(get("/beneficiario/all")
                            .header("matricola", "12345")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }catch (Exception e){
            throw e;
        }
    }

}
