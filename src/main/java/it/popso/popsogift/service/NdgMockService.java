package it.popso.popsogift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NdgMockService {
    private static final String NDGMOCKURL ="https://a0f8f4b4-eaa4-456a-88ea-c67fd0e370ab.mock.pstmn.io";
    private final RestTemplate restTemplate;
    @Autowired
    public NdgMockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String getNdgMocked(){
        return restTemplate.getForObject(NDGMOCKURL,String.class);
    }
}
