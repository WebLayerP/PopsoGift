package it.popso.popsogift.service;

import it.popso.popsogift.dto.*;
import it.popso.popsogift.repository.BeneficiarioRepository;
import it.popso.popsogift.repository.CampagnaRepository;
import it.popso.popsogift.repository.FornitoreRepository;
import it.popso.popsogift.repository.OggettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OverviewService {

        public static final String RE = "RE";
        public static final String RA = "RA";
        public static final String RF = "RF";
        @Autowired
        private CampagnaRepository campagnaRepository;

        @Autowired
        private BeneficiarioRepository beneficiarioRepository;

        @Autowired
        private OggettoRepository oggettoRepository;

        @Autowired
        private FornitoreRepository fornitoreRepository;

        public CampagnaOverview getCampagnaOverview(int idCampagna){
                List<Object[]>  results = campagnaRepository.findAllCampagnaByIdCampagna(idCampagna);
                CampagnaOverview campagnaOverview = new CampagnaOverview();
                for(Object[] o: results){
                        if(o[1]!= null) {
                                campagnaOverview.setTipologia(castObjectIntValue(o[1]));
                        }
                        if(o[2]!= null) {
                                campagnaOverview.setNumeroOmaggi(castObjectIntValue(o[2]));
                        }
                        if(o[3] != null){
                                campagnaOverview.setTotCosto(castObjectIntValue(o[3]));
                        }
                        if(o[4] != null){
                                campagnaOverview.setDataFineCampagna((Date) o[4]);
                        }
                        if(o[5] != null) campagnaOverview.setFilialiInAttesa(castObjectIntValue(o[5]));
                        if(o[6] != null){
                                campagnaOverview.setFilialiConfermate(castObjectIntValue(o[6]));
                        }
                }
                return campagnaOverview;
        }


        public CampagnaGroup getCampagneOverview(String ruolo, List<String> codiciFiliale){
                List<Object[]>  results = new ArrayList<>();
                if(ruolo.equals(RE)){
                        results = campagnaRepository.findAllCampagnaGroupByStato();
                }
                else if(ruolo.equals(RA) || ruolo.equals(RF)){
                        results = campagnaRepository.findAllCampagnaGroupByStatoFiliali(codiciFiliale);
                }
                List<CampagnaDTO> listaCampagneSegnalazioni = new ArrayList<>();
                CampagnaGroup campagnaGroup = new CampagnaGroup();
                Integer anno = LocalDate.now().getYear();
                campagnaGroup.setAnno(anno);
                campagnaGroup.setDataUltimoAggiornamento(campagnaRepository.findMaxDataAggiornamentoForYear(anno));
                for(Object[] o: results){
                        listaSegnalazioniPerCampagna(o, listaCampagneSegnalazioni);
                        int idStato = castObjectIntValue(o[1]);
                        int numeroCampagne = castObjectIntValue(o[0]);
                        if(idStato == StatoDTO.IN_CORSO.getIdStato()){
                                campagnaGroup.setNumeroCampagneInCorso(numeroCampagne);
                        }
                        if(idStato == StatoDTO.BOZZA.getIdStato()){
                                campagnaGroup.setNumeroCampagneBozza(numeroCampagne);
                        }
                        if(idStato == StatoDTO.CHIUSA.getIdStato()){
                                campagnaGroup.setNumeroCampagneChiuse(numeroCampagne);
                        }
                }
                campagnaGroup.setListaCampagneConSegnalazioni(listaCampagneSegnalazioni);
                return campagnaGroup;
        }

        public PanoramicaOverview getAnagraficaOverview() {
                List<Object[]> beneficiariAndPrivacy = beneficiarioRepository.countBeneficiariAndPrivacy();
                PanoramicaOverview panoramicaOverview = new PanoramicaOverview();
                for (Object[] o : beneficiariAndPrivacy) {
                        if(o[0] != null) {
                                panoramicaOverview.setNumeroBeneficiari(((Long) o[0]).intValue());
                        }
                        if(o[1] != null) {
                                panoramicaOverview.setNumeroPrivacyKO(((Long) o[1]).intValue());
                        }
                        if(o[2] != null) {
                                panoramicaOverview.setDataAggiornamento(((Date) o[2]));
                        }
                }
                return panoramicaOverview;
        }
        public OggettoOverview getOggettoOverview(){
                OggettoOverview result = new OggettoOverview();
                List<Object[]> risultatiQuery = oggettoRepository.numeroOggettiGroupByTipologia();
                for (Object[] o : risultatiQuery) {
                        if((int) o[0] == 1 )
                                result.setNumeroOggettiFisici((long)o[1]);
                        else if ((int) o[0] == 2 )
                                result.setNumeroOggettiDigitali((long)o[1]);
                }
                result.setNumeroFornitori(fornitoreRepository.findNumeroFornitoriTotali());
                result.setDataUltimoAggiornamento(oggettoRepository.findMaxByDataAggiornamento());
                return result;
        }


        private void listaSegnalazioniPerCampagna(Object[] o, List<CampagnaDTO> listaCampagneSegnalazioni){
                if(o[4]!= null) {
                        int numeroSegnalazioniPerCampagna = 0;
                        if(listaCampagneSegnalazioni.stream()
                                .noneMatch(c -> c.getIdCampagna().equals((castObjectIntValue(o[8]))))){
                                numeroSegnalazioniPerCampagna ++;
                                CampagnaDTO campagnaDTO = new CampagnaDTO();
                                campagnaDTO.setIdCampagna((castObjectIntValue(o[8])));
                                campagnaDTO.setTitoloCampagna(o[3].toString());
                                campagnaDTO.setNumeroSegnalazioni(numeroSegnalazioniPerCampagna);
                                listaCampagneSegnalazioni.add(campagnaDTO);
                        }
                        else{
                                listaCampagneSegnalazioni.stream()
                                        .filter(campagnaDTO -> campagnaDTO.getIdCampagna().equals((castObjectIntValue(o[8])))).findFirst()
                                        .ifPresent(campagna -> campagna.setNumeroSegnalazioni(campagna.getNumeroSegnalazioni()+1));
                        }

                }
        }

        private int castObjectIntValue(Object o){
                if(o instanceof Float)
                        return ((Float) o).intValue();
                if(o instanceof Long)
                        return ((Long) o).intValue();
                if(o instanceof Double)
                        return ((Double) o).intValue();
                if(o instanceof BigDecimal)
                        return ((BigDecimal) o).intValue();
                return ((Integer) o).intValue();
        }
}
