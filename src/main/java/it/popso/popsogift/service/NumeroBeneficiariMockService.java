package it.popso.popsogift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumeroBeneficiariMockService {
    private static final String NBENEFICIARIMOCKURL ="https://8eb53bc1-65d9-4a05-927c-d4839df0c85d.mock.pstmn.io";
    private final RestTemplate restTemplate;
    @Autowired
    public NumeroBeneficiariMockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String getNBeneficiariMocked(){
        return restTemplate.getForObject(NBENEFICIARIMOCKURL,String.class);
    }
}

