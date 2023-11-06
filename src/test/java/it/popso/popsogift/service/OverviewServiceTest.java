package it.popso.popsogift.service;

import it.popso.popsogift.PopsogiftApplication;
import it.popso.popsogift.dto.CampagnaGroup;
import it.popso.popsogift.dto.StatoBeneficiarioDTO;
import it.popso.popsogift.dto.StatoDTO;
import it.popso.popsogift.dto.TipologiaDTO;
import it.popso.popsogift.entity.*;
import it.popso.popsogift.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
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
class OverviewServiceTest {

    @Autowired
    private CampagnaRepository campagnaRepository;
    @Autowired
    private StatoRepository statoRepository;
    @Autowired
    private FilialeRepository filialeRepository;
    @Autowired
    private TipologiaRepository tipologiaRepository;
    @Autowired
    private StatoBeneficiarioRepository statoBeneficiarioRepository;
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    @Autowired
    private SegnalazioneRepository segnalazioneRepository;
    @Autowired
    private OverviewService overviewService;

    public Campagna campagna = new Campagna();
    private Stato stato = new Stato();
    private Tipologia tipologia = new Tipologia();
    private Segnalazione segnalazione = new Segnalazione();
    private Filiale filiale = new Filiale();
    private Beneficiario beneficiario = new Beneficiario();
    private StatoBeneficiario statoBeneficiario = new StatoBeneficiario();

    @BeforeEach
    public void init() throws SQLException {

        stato.setIdStato(1);
        stato.setNomeStato(StatoDTO.IN_CORSO);
        statoRepository.save(stato);

        tipologia.setIdTipologia(1);
        tipologia.setNomeTipologia(TipologiaDTO.FISICA);
        tipologiaRepository.save(tipologia);

        filiale.setCodiceFiliale("F0001");
        filialeRepository.save(filiale);
        List<Filiale> listaFiliali = new ArrayList<>();
        listaFiliali.add(filiale);

        statoBeneficiario.setIdStato(1);
        statoBeneficiario.setNomeStato(StatoBeneficiarioDTO.OK);
        statoBeneficiarioRepository.save(statoBeneficiario);

        beneficiario.setNdg("12345");
        beneficiario.setDataInserimento(new Date());
        beneficiario.setStatoBeneficiario(statoBeneficiario);
        beneficiario.setFiliale(filiale);
        beneficiarioRepository.save(beneficiario);

        campagna
                .setDataAggiornamento(Date.from(LocalDate.of(2023, 10, 2).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        campagna
                .setDataFineModifiche(Date.from(LocalDate.of(2023, 11, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        campagna
                .setDataInizioModifiche(Date.from(LocalDate.of(2023, 10, 3).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        campagna.setDataInserimento(new Date());
        campagna.setDataFine(Date.from(LocalDate.of(2023, 11, 25).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        campagna.setIdCampagna(1);
        campagna.setListaFiliali(listaFiliali);
        campagna.setListaOmaggi(new ArrayList<>());
        campagna.setMatricola("Matricola");
        campagna.setSegnalazione(new ArrayList<>());
        campagna.setStato(stato);
        campagna.setTipologia(tipologia);
        campagna.setTitoloCampagna("Titolo Campagna");
        campagnaRepository.save(campagna);

        segnalazione.setDataInserimento(new Date());
        segnalazione.setAutore("Test");
        segnalazione.setDescrizione("Segnalazione di test overview campagna");
        segnalazione.setId(1);
        segnalazione.setBeneficiario(beneficiario);
        segnalazione.setCampagna(campagna);
        segnalazioneRepository.save(segnalazione);
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
}

