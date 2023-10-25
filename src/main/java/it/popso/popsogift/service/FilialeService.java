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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class FilialeService {

    public static final String INDIRIZZO = "indirizzo";
    public static final String DESCRIZIONE = "descrizione";
    public static final String CODICE = "codice";
    public static final String DIPENDENZA = "dipendenza";
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${filiali.service.url}")
    private String filialiServiceUrl;
    @Value("${idtipo.parameters}")
    private List<Integer> parameters;

    @Autowired
    private final NumeroBeneficiariMockService numeroBeneficiariMockService;

    private final ConcurrentHashMap<Integer,String> xmlResponseCache = new ConcurrentHashMap<>();

    public FilialeService(NumeroBeneficiariMockService numeroBeneficiariMockService) {
        this.numeroBeneficiariMockService = numeroBeneficiariMockService;
    }

    public List<FilialeDTO> getAllFilialeDTO() {
        List<FilialeDTO> fullListDatiFiliali = new ArrayList<>();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (Integer parameter : parameters) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
                ResponseEntity<String> responseEntity;
                String fullUrl = filialiServiceUrl + parameter;
                String cachedXmlResponse = xmlResponseCache.get(parameter);
                if(cachedXmlResponse!= null){
                    responseEntity = ResponseEntity.status(HttpStatus.OK).body(cachedXmlResponse);
                }
                else {
                    responseEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, null, String.class);
                    xmlResponseCache.put(parameter, Objects.requireNonNull(responseEntity.getBody()));
                }
                if (responseEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
                    fullListDatiFiliali.addAll(getFilialiDTO(responseEntity.getBody()));
                }
            });
            futures.add(future);

        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        try{
            allOf.get(15, TimeUnit.SECONDS);
        } catch(ExecutionException | TimeoutException | InterruptedException e){
            Thread.currentThread().interrupt();
            throw new InputFaultMsgException("Errore chiamate servizio filiali");
        }
        return fullListDatiFiliali.stream().sorted(Comparator.comparing(FilialeDTO::getNomeFiliale)).collect(Collectors.toList());
    }

    public List<FilialeDTO> getFilialiDTO(String risultatiXml) {
        return  mapXmlToListaFilialeDTO(risultatiXml);
    }
    private List<FilialeDTO> mapXmlToListaFilialeDTO(String risultatiXml){
        List<FilialeDTO> listaFilialiDTO = new ArrayList<>();
        //Integer numeroBeneficiari = Integer.parseInt(numeroBeneficiariMockService.getNBeneficiariMocked());
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
               // filialeDTO.setNumeroBeneficiari(numeroBeneficiari);
                listaFilialiDTO.add(filialeDTO);
            }
        } catch(Exception e){
            throw new InputFaultMsgException("Errore lettura dati da XML");
        }
        return listaFilialiDTO;
    }
}