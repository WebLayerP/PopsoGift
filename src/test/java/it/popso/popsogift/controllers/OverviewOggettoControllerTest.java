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
public class OverviewOggettoControllerTest {

    @Autowired
    private OverviewOggettoController overviewOggettoController;


    @Test
            public void overviewOggetto_thenStatus200()
            throws Exception {

                // Inizializza il logger per il test
                MockMvc mockMvc = MockMvcBuilders.standaloneSetup(overviewOggettoController)
                        .build();

                try {
                    mockMvc.perform(get("/overview/catalogo")
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
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(overviewOggettoController)
                .build();

        try {
            mockMvc.perform(get("/overview/catalogo")
                            .header("matricola", "12345")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }catch (Exception e){
            throw e;
        }
    }

    @Test
    public void overviewCatalogoDetail_thenStatus200()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(overviewOggettoController)
                .build();

            mockMvc.perform(get("/overview/campagna/10")
                            .header("ruolo", "RE")
                            .header("matricola", "12345")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    public void overviewCatalogoDetail_withoutheader_thenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(overviewOggettoController)
                .build();

            mockMvc.perform(get("/overview/campagna/1")
                            .header("matricola", "12345")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
    }

}
