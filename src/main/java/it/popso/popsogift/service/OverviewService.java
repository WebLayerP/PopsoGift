package it.popso.popsogift.service;

import it.popso.popsogift.dto.*;
import it.popso.popsogift.repository.BeneficiarioRepository;
import it.popso.popsogift.repository.CampagnaRepository;
import it.popso.popsogift.repository.FornitoreRepository;
import it.popso.popsogift.repository.OggettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OverviewService {

        @Autowired
        private CampagnaRepository campagnaRepository;

        @Autowired
        private BeneficiarioRepository beneficiarioRepository;

        @Autowired
        private OggettoRepository oggettoRepository;

        @Autowired
        private FornitoreRepository fornitoreRepository;

        public CampagnaGroup getCampagneOverview(){
                List<Object[]>  results = campagnaRepository.findAllCampagnaGroupByStato();
                List<CampagnaDTO> listaCampagneSegnalazioni = new ArrayList<>();
                Integer contatoreSegnalazioni = 0;
                CampagnaGroup campagnaGroup = new CampagnaGroup();
                Integer anno = LocalDate.now().getYear();
                campagnaGroup.setAnno(anno);
                campagnaGroup.setDataUltimoAggiornamento(campagnaRepository.findMaxDataAggiornamentoForYear(anno));
                for(Object[] o: results){
                        if(o[8]!= null) {
                                contatoreSegnalazioni++;
                                CampagnaDTO campagnaDTO = new CampagnaDTO();
                                campagnaDTO.setIdCampagna(((Float) o[8]).intValue());
                                campagnaDTO.setTitoloCampagna(o[6].toString());
                                listaCampagneSegnalazioni.add(campagnaDTO);
                        }
                        if(((Float) o[1]).intValue() == StatoDTO.IN_CORSO.getIdStato()){
                                campagnaGroup.setNumeroCampagneInCorso(((Float) o[0]).intValue());
                        }
                        if(((Float) o[1]).intValue() == StatoDTO.BOZZA.getIdStato()){
                                campagnaGroup.setNumeroCampagneBozza(((Float) o[0]).intValue());
                        }
                        if(((Float) o[1]).intValue() == StatoDTO.CHIUSA.getIdStato()){
                                campagnaGroup.setNumeroCampagneChiuse(((Float) o[0]).intValue());
                        }
                }
                campagnaGroup.setListaCampagneConSegnalazioni(listaCampagneSegnalazioni);
                campagnaGroup.setNumeroCampagneConSegnalazioni(contatoreSegnalazioni);
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
                        //TODO manca settare anche mailKO e indirizziKO
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
                result.setNumeroFornitori(fornitoreRepository.findNumeroFornitoriTorali());
                result.setDataUltimoAggiornamento(oggettoRepository.findMaxByDataAggiornamento());
                return result;
        }
}
