package it.popso.popsogift.utils;

import it.popso.popsogift.dto.StatoBeneficiarioDTO;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.entity.Gruppo;
import it.popso.popsogift.entity.StatoBeneficiario;
import it.popso.popsogift.repository.BeneficiarioRepository;
import it.popso.popsogift.repository.FilialeRepository;
import it.popso.popsogift.repository.GruppoRepository;
import it.popso.popsogift.repository.StatoBeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class BeneficiarioDataInitializer {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private StatoBeneficiarioRepository statoBeneficiarioRepository;

    @Autowired
    private FilialeRepository filialeRepository;

    @Autowired
    private GruppoRepository gruppoRepository;

    public void initializeBeneficiarioData() {
    Beneficiario beneficiario = new Beneficiario();
    beneficiario.setNdg(UUID.randomUUID().toString().substring(0,4));
    beneficiario.setDataInserimento(new Date());
    StatoBeneficiario statoBeneficiario = new StatoBeneficiario();
    statoBeneficiario.setIdStato(1);
    statoBeneficiario.setNomeStato(StatoBeneficiarioDTO.OK);
    statoBeneficiarioRepository.save(statoBeneficiario);
    beneficiario.setStatoBeneficiario(statoBeneficiario);
    Gruppo gruppo = new Gruppo();
    gruppo.setDataAggiornamento(new Date());
    gruppo.setDescrizione("descr");
    gruppo.setDataInserimento(new Date());
    gruppo.setIdGruppo(1);
    gruppo.setNome("nomeGruppo");
    List<Gruppo> listaGruppi = new ArrayList<>();
    listaGruppi.add(gruppo);
    beneficiario.setListaGruppi(listaGruppi);
    gruppoRepository.save(gruppo);
    beneficiario.setDataAggiornamento(new Date());
    beneficiario.setNote("nessuna");
    beneficiario.setPrivacy(Boolean.FALSE);
    beneficiarioRepository.save(beneficiario);
    }

    public void insertNewBeneficiario() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNdg("2");
        beneficiario.setDataInserimento(new Date());
        StatoBeneficiario statoBeneficiario = new StatoBeneficiario();
        statoBeneficiario.setIdStato(1);
        statoBeneficiario.setNomeStato(StatoBeneficiarioDTO.OK);
        statoBeneficiarioRepository.save(statoBeneficiario);
        beneficiario.setStatoBeneficiario(statoBeneficiario);
        Gruppo gruppo = new Gruppo();
        gruppo.setDataAggiornamento(new Date());
        gruppo.setDescrizione("descr");
        gruppo.setDataInserimento(new Date());
        gruppo.setIdGruppo(1);
        gruppo.setNome("nomeGruppo");
        List<Gruppo> listaGruppi = new ArrayList<>();
        listaGruppi.add(gruppo);
        beneficiario.setListaGruppi(listaGruppi);
        gruppoRepository.save(gruppo);
        beneficiario.setNote("nessuna");
        beneficiario.setPrivacy(Boolean.TRUE);
        beneficiarioRepository.save(beneficiario);
    }
}
