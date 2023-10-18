package it.popso.popsogift.service;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.exceptions.InputFaultMsgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> parameters;

    @Value("${factory.featureone}")
    private String featureOne;

    @Value("${factory.featuretwo}")
    private String featureTwo;

    @Value("${factory.featurethree}")
    private String featureThree;

    @Autowired
    private final NumeroBeneficiariMockService numeroBeneficiariMockService;

    public FilialeService(NumeroBeneficiariMockService numeroBeneficiariMockService) {
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
        List<FilialeDTO> listaFilialiDTO = new ArrayList<>();
        Integer numeroBeneficiari = Integer.parseInt(numeroBeneficiariMockService.getNBeneficiariMocked());
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(featureOne,false);
            factory.setFeature(featureTwo,false);
            factory.setFeature(featureThree,false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc =builder.parse(new InputSource(new StringReader(risultatiXml)));
            NodeList dipendenzaNodes = doc.getElementsByTagName(DIPENDENZA);
            for(int i=0; i<dipendenzaNodes.getLength(); i++){
                FilialeDTO filialeDTO = new FilialeDTO();
                Node dipendenzaNode = dipendenzaNodes.item(i);
                NodeList campiNodes = dipendenzaNode.getChildNodes();
                for(int j=0; j< campiNodes.getLength(); j++){
                    Node campoNode = campiNodes.item(j);
                    if(campoNode.getNodeType()== Node.ELEMENT_NODE){
                        String nodeName = campoNode.getNodeName();
                        String nodeValue = campoNode.getTextContent();
                        setCampi(filialeDTO, nodeName, nodeValue);
                    }
                }
                filialeDTO.setNumeroBeneficiari(numeroBeneficiari);
                listaFilialiDTO.add(filialeDTO);
            }
        } catch (Exception e) {
            throw new InputFaultMsgException("Errore salvataggio filialeDTO");
        }
        return listaFilialiDTO;
    }

    private static void setCampi(FilialeDTO filialeDTO, String nodeName, String nodeValue) {
        if(INDIRIZZO.equals(nodeName)){
            filialeDTO.setIndirizzo(nodeValue);
        }
        if(DESCRIZIONE.equals(nodeName)){
            filialeDTO.setNomeFiliale(nodeValue);
        }
        if(CODICE.equals(nodeName)) {
            filialeDTO.setCodiceFiliale(nodeValue);
        }
    }
}