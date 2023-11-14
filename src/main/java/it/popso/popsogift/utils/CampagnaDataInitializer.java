package it.popso.popsogift.utils;

import it.popso.popsogift.dto.StatoBeneficiarioDTO;
import it.popso.popsogift.dto.StatoDTO;
import it.popso.popsogift.dto.TipologiaDTO;
import it.popso.popsogift.entity.*;
import it.popso.popsogift.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CampagnaDataInitializer {
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

    public void initializeCampagnaData() {

        Stato stato = new Stato();
        stato.setIdStato(1);
        stato.setNomeStato(StatoDTO.IN_CORSO);
        statoRepository.save(stato);

        Tipologia tipologia = new Tipologia();
        tipologia.setIdTipologia(1);
        tipologia.setNomeTipologia(TipologiaDTO.FISICA);
        tipologiaRepository.save(tipologia);

        StatoBeneficiario statoBeneficiario = new StatoBeneficiario();
        statoBeneficiario.setIdStato(1);
        statoBeneficiario.setNomeStato(StatoBeneficiarioDTO.OK);
        statoBeneficiarioRepository.save(statoBeneficiario);

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNdg("12345");
        beneficiario.setDataInserimento(new Date());
        beneficiario.setStatoBeneficiario(statoBeneficiario);
        beneficiarioRepository.save(beneficiario);

        Filiale filiale = new Filiale();
        filiale.setCodiceFiliale("F0001");
        List<Beneficiario> listaBeneficiari = new ArrayList<>();
        listaBeneficiari.add(beneficiario);
        filiale.setListaBeneficiari(listaBeneficiari);
        filialeRepository.save(filiale);
        List<Filiale> listaFiliali = new ArrayList<>();
        listaFiliali.add(filiale);

        Campagna campagna = new Campagna();
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

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setDataInserimento(new Date());
        segnalazione.setAutore("Test");
        segnalazione.setDescrizione("Segnalazione di test overview campagna");
        segnalazione.setId(1);
        segnalazione.setBeneficiario(beneficiario);
        segnalazione.setCampagna(campagna);
        segnalazioneRepository.save(segnalazione);

        Segnalazione segnalazione1 = new Segnalazione();
        segnalazione1.setDataInserimento(new Date());
        segnalazione1.setAutore("Test");
        segnalazione1.setDescrizione("Segnalazione  2 di test overview campagna");
        segnalazione1.setId(2);
        segnalazione1.setBeneficiario(beneficiario);
        segnalazione1.setCampagna(campagna);
        segnalazioneRepository.save(segnalazione1);
    }
}