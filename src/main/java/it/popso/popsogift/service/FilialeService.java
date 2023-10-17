package it.popso.popsogift.service;

import it.popso.popsogift.exceptions.InputFaultMsgException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilialeService {

    private RestTemplate restTemplate = new RestTemplate();
    @Value("${filiali.service.url}")
    private String filialiServiceUrl;
    @Value("${idtipo.parameters}")
    private List<String> parameters;

    public List<Map<String,String>> getFilialiTipo() {
        ResponseEntity<String> responseEntity = null;
        List<Map<String,String>> fullListDatiFiliali = new ArrayList<>();
        for (String parameter : parameters) {
            String fullUrl = filialiServiceUrl + parameter;
            responseEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, null, String.class);
            if (responseEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
                fullListDatiFiliali.addAll(saveRisultatiXml(responseEntity.getBody()));
            }
        }
        if(responseEntity == null){
            throw new InputFaultMsgException("Errore di chiamata al servizio");
        }
        return fullListDatiFiliali;
    }

    public List<Map<String,String>> saveRisultatiXml(String risultatiXml) {
        List<Map<String,String>> dipendenze = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
            factory.setFeature("http://xml.org/sax/features/external-general-entities",false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities",false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc =builder.parse(new InputSource(new StringReader(risultatiXml)));
            NodeList dipendenzaNodes = doc.getElementsByTagName("dipendenza");
            for(int i=0; i<dipendenzaNodes.getLength(); i++){
                Node dipendenzaNode = dipendenzaNodes.item(i);
                Map<String,String> dipendenza = new HashMap<>();
                NodeList campiNodes = dipendenzaNode.getChildNodes();
                for(int j=0; j< campiNodes.getLength(); j++){
                    Node campoNode = campiNodes.item(j);
                    if(campoNode.getNodeType()== Node.ELEMENT_NODE){
                        String nodeName = campoNode.getNodeName();
                        String nodeValue = campoNode.getTextContent();
                        if("indirizzo".equals(nodeName) || "descrizione".equals(nodeName) || "codice".equals(nodeName)) {
                            dipendenza.put(nodeName,nodeValue);
                        }
                    }
                }
                dipendenze.add(dipendenza);
            }
        } catch (Exception e) {
            throw new InputFaultMsgException("Errore salvataggio dipendenze");
        }
        return dipendenze;
    }

    private void leggiCampiNidificati(Node node,List<String> elementi){
        NodeList childNodes = node.getChildNodes();
        for(int i=0; i< childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                elementi.add(node.getNodeName() + ": " + node.getTextContent());
                leggiCampiNidificati(childNode,elementi);
            }
        }
    }
}