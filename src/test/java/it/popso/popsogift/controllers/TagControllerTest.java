package it.popso.popsogift.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.popso.popsogift.PopsogiftApplication;
import it.popso.popsogift.dto.TagDTO;
import it.popso.popsogift.entity.Tag;
import it.popso.popsogift.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PopsogiftApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class TagControllerTest {

    @Autowired
    private TagController tagController;

    @Autowired
    private TagService tagService;

    @Test
    public void insertTagThenStatus200()
            throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(tagToSave("provaInsert", "prova inserimento"));

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();


        mockMvc.perform(post("/tag")
                        .header("ruolo", "RE")
                        .header("matricola", "12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void insertTagThenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();

        mockMvc.perform(post("/tag")
                        .header("Matricola", "12345")
                        .header("Ruolo", "RE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateTagThenStatus200()
            throws Exception {

        TagDTO tagDTO = tagToSave("provaUpdate", "prova update");
        Tag tag = tagService.saveTag(tagDTO, "456464");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(tagDTO);

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();


        mockMvc.perform(put("/tag/"+tag.getIdTag())
                        .header("ruolo", "RE")
                        .header("matricola", "12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTagThenStatus400()
            throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(new Tag());

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();

        mockMvc.perform(put("/tag/536")
                        .header("ruolo", "RE")
                        .header("matricola", "12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void findTagByIdThenStatus200()
            throws Exception {

        TagDTO tagDTO = tagToSave("provaFind", "prova find by id");

        Tag tag = tagService.saveTag(tagDTO, "12343");

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();


        mockMvc.perform(get("/tag/"+tag.getIdTag())
                        .header("ruolo", "RE")
                        .header("matricola", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findTagByIdThenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();

        mockMvc.perform(get("/tag/536")
                        .header("ruolo", "RE")
                        .header("matricola", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void dynamicSearchThenStatus200()
            throws Exception {

        TagDTO tagDTO = tagToSave("provaDynamicSearch", "prova dynamic search");

        tagService.saveTag(tagDTO, "12343");

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();


        mockMvc.perform(get("/tag/lista-dinamica")
                        .param("tag", "provaDynamicSearch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void dynamicSearchThenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();

        mockMvc.perform(get("/tag/lista-dinamica")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void searchOrderByDtInsDescThenStatus200()
            throws Exception {

        TagDTO tagDTO = tagToSave("provaDynamicSearch", "prova dynamic search");

        tagService.saveTag(tagDTO, "12343");

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();


        mockMvc.perform(get("/tag")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void searchOrderByNomeTagAscThenStatus200()
            throws Exception {

        TagDTO tagDTO = tagToSave("provaDynamicSearch", "prova dynamic search");

        tagService.saveTag(tagDTO, "12343");

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();


        mockMvc.perform(get("/tag")
                        .param("page", "0")
                        .param("size", "5")
                        .param("order", "ASC")
                        .param("orderBy", "nomeTag")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void searchThenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();

        mockMvc.perform(get("/tag")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteThenStatus200()
            throws Exception {

        TagDTO tagDTO = tagToSave("provaDynamicSearch", "prova dynamic search");

        Tag tag = tagService.saveTag(tagDTO, "12343");

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();


        mockMvc.perform(delete("/tag/"+ tag.getIdTag())
                        .header("ruolo", "RE")
                        .header("matricola", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteThenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController)
                .build();

        mockMvc.perform(delete("/tag/35")
                        .header("ruolo", "RE")
                        .header("matricola", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private TagDTO tagToSave(String nome, String descrizione){
        TagDTO tagDTO = new TagDTO();
        tagDTO.setNomeTag(nome);
        tagDTO.setDescrizione(descrizione);
        return tagDTO;
    }
}
