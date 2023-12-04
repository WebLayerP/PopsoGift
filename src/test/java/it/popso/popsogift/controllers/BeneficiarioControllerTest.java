package it.popso.popsogift.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.popso.popsogift.PopsogiftApplication;
import it.popso.popsogift.dto.*;
import it.popso.popsogift.entity.*;
import it.popso.popsogift.mapper.BeneficiarioMapper;
import it.popso.popsogift.mapper.GruppoMapper;
import it.popso.popsogift.mapper.OggettoMapper;
import it.popso.popsogift.repository.*;
import it.popso.popsogift.service.BeneficiarioService;
import it.popso.popsogift.utils.BeneficiarioDataInitializer;
import it.popso.popsogift.utils.CampagnaDataInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Autowired
    private GruppoMapper gruppoMapper;


    @Autowired
    private TipologiaOggettoRepository tipologiaOggettoRepository;

    @Autowired
    private OggettoMapper oggettoMapper;

    @Autowired
    private CampagnaDataInitializer campagnaDataInitializer;

    @Autowired
    private CampagnaRepository campagnaRepository;

    @Autowired
    private OggettoRepository oggettoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private BeneficiarioMapper beneficiarioMapper;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private StatoBeneficiarioRepository statoBeneficiarioRepository;

    @Autowired
    private BeneficiarioDataInitializer beneficiarioDataInitializer;


    @Test
    public void getAllBeneficiario_thenStatus200()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        try {
            mockMvc.perform(get("/beneficiari/all")
                            .header("Ruolo", "RE")
                            .header("Matricola", "12345")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            throw e;
        }
    }

    @Test
    public void overviewBeneficiario_withoutheader_thenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        try {
            mockMvc.perform(get("/beneficiari/all")
                            .header("matricola", "12345")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }catch (Exception e){
            throw e;
        }
    }

    @Test
    public void insertBeneficiariothenStatus200()
            throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(mockBeneficiarioDTO());


        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        mockMvc.perform(post("/beneficiari")
                        .header("Matricola", "12345")
                        .header("Ruolo", "RE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void insertBeneficiariothenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        mockMvc.perform(post("/beneficiari")
                        .header("Matricola", "12345")
                        .header("Ruolo", "RE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    public BeneficiarioDTO mockBeneficiarioDTO() {
        BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
        beneficiarioDTO.setNdg("NDG300");
        beneficiarioDTO.setDataInserimento(new Date());
        StatoBeneficiarioDTO statoBeneficiarioDTO = StatoBeneficiarioDTO.OK;
        StatoBeneficiario statoBeneficiario = new StatoBeneficiario();
        statoBeneficiario.setIdStato(StatoBeneficiarioDTO.OK.getIdStato());
        statoBeneficiario.setNomeStato(statoBeneficiarioDTO);
        statoBeneficiarioRepository.save(statoBeneficiario);
        beneficiarioDTO.setStatoBeneficiario(statoBeneficiarioDTO);
        beneficiarioDTO.setStatoCancellazione(false);
        GruppoDTO gruppoDTO = new GruppoDTO();
        gruppoDTO.setDataAggiornamento(new Date());
        gruppoDTO.setDescrizione("descr");
        gruppoDTO.setDataInserimento(new Date());
        gruppoDTO.setIdGruppo(1);
        gruppoDTO.setNome("nomeGruppo");
        List<GruppoDTO> listaGruppiDTO = new ArrayList<>();
        listaGruppiDTO.add(gruppoDTO);
        beneficiarioDTO.setListaGruppi(listaGruppiDTO);
        beneficiarioDTO.setDataAggiornamento(new Date());
        beneficiarioDTO.setNote("nessuna");
        beneficiarioDTO.setPrivacy(Boolean.FALSE);
        List<Oggetto> oggettoList = new ArrayList<>();
        OggettoDTO oggettoDTO = new OggettoDTO();
        oggettoDTO.setIdOggetto(1);
        oggettoDTO.setCodice("GE3WKS");
        oggettoDTO.setCategoria("CAT");
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("CAT");
        categoriaRepository.save(categoria);
        TipologiaOggetto tipologiaOggetto = new TipologiaOggetto();
        tipologiaOggetto.setIdTipologia(TipologiaOggettoDTO.DIGITALE.getIdTipologiaOggetto());
        tipologiaOggetto.setNomeTipologia(TipologiaOggettoDTO.DIGITALE);
        tipologiaOggettoRepository.save(tipologiaOggetto);
        oggettoDTO.setTipologia(tipologiaOggetto.getNomeTipologia());
        Oggetto oggetto = oggettoMapper.oggettoDTOToOggetto(oggettoDTO);
        oggettoList.add(oggetto);
        oggettoRepository.save(oggetto);
        List<OggettoDTO> oggettiDTO = new ArrayList<>();
        oggettiDTO.add(oggettoDTO);
        beneficiarioDTO.setListaOggetti(oggettiDTO);
        Beneficiario beneficiario = beneficiarioMapper.beneficiarioDTOToBeneficiario(beneficiarioDTO);
        beneficiario.setListaGruppi(gruppoMapper.lgruppoDTOToGruppo(beneficiarioDTO.getListaGruppi()));
        beneficiario.setListaOggetti(oggettoMapper.listaOggettiDTOToEntity(beneficiarioDTO.getListaOggetti()));
        beneficiarioRepository.save(beneficiario);
        return beneficiarioDTO;
    }

    @Test
    public void listBeneficiariothenStatus200()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        mockMvc.perform(get("/beneficiari/lista")
                        .header("Ruolo", "RE")
                        .header("Matricola", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void listBeneficiarithenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        mockMvc.perform(get("/beneficiari/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void beneficiarioByIdthenStatus200()
            throws Exception {


        BeneficiarioDTO beneficiario = mockBeneficiarioDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(beneficiario);


        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        mockMvc.perform(get("/beneficiari/"+ beneficiario.getNdg())
                        .header("Matricola", "12345")
                        .header("Ruolo", "RE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }


    @Test
    public void beneficiarioByIdthenStatus400()
            throws Exception {

        // Inizializza il logger per il test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(beneficiarioController)
                .build();

        mockMvc.perform(get("/beneficiari/NDG300")
                        .header("Matricola", "12345")
                        .header("Ruolo", "RE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }



}
