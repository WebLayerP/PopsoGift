package it.popso.popsogift.service;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.exceptions.InputFaultMsgException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilialeServiceSecond {

    public static final String INDIRIZZO = "indirizzo";
    public static final String DESCRIZIONE = "descrizione";
    public static final String CODICE = "codice";
    public static final String DIPENDENZA = "dipendenza";
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${filiali.service.url}")
    private String filialiServiceUrl;
    @Value("${idtipo.parameters}")
    private List<String> parameters;

    @Autowired
    private final NumeroBeneficiariMockService numeroBeneficiariMockService;

    public FilialeServiceSecond(NumeroBeneficiariMockService numeroBeneficiariMockService) {
        this.numeroBeneficiariMockService = numeroBeneficiariMockService;
    }

    public List<FilialeDTO> getAllFilialeDTO() {
        ResponseEntity<String> responseEntity = null;
        List<FilialeDTO> fullListDatiFiliali = new ArrayList<>();
        for (String parameter : parameters) {
            String fullUrl = filialiServiceUrl + parameter;
            responseEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, null, String.class);
            if (responseEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
                fullListDatiFiliali.addAll(getFilialiDTO(responseEntity.getBody()));
            }
        }
        if(responseEntity == null){
            throw new InputFaultMsgException("Errore di chiamata al servizio");
        }
        return fullListDatiFiliali;
    }

    public List<FilialeDTO> getFilialiDTO(String risultatiXml) {
        return  mapXmlToListaFilialeDTO(risultatiXml);
    }
    private List<FilialeDTO> mapXmlToListaFilialeDTO(String risultatiXml){
        List<FilialeDTO> listaFilialiDTO = new ArrayList<>();
        Integer numeroBeneficiari = Integer.parseInt(numeroBeneficiariMockService.getNBeneficiariMocked());
        try{
            Document document = Jsoup.parse(risultatiXml);
            Elements dipendenzaElements = document.select(DIPENDENZA);
            for(Element dipendenzaElement: dipendenzaElements){
                FilialeDTO filialeDTO = new FilialeDTO();
                Element codiceElement = dipendenzaElement.select(CODICE).first();
                filialeDTO.setCodiceFiliale(Objects.requireNonNull(codiceElement).text());
                Element descrizioneElement = dipendenzaElement.select(DESCRIZIONE).first();
                filialeDTO.setNomeFiliale(Objects.requireNonNull(descrizioneElement).text());
                Element indirizzoElement = dipendenzaElement.select(INDIRIZZO).first();
                filialeDTO.setIndirizzo(Objects.requireNonNull(indirizzoElement).text());
                filialeDTO.setNumeroBeneficiari(numeroBeneficiari);
                listaFilialiDTO.add(filialeDTO);
            }
        } catch(Exception e){
            throw new InputFaultMsgException("Errore lettura dati da XML");
        }
        return listaFilialiDTO;
    }
}