package com.apap.ta46.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.PasienModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {
	@Autowired
    RestTemplate restTemplate;
    
    @Bean
    public RestTemplate rest() {
    	return new RestTemplate();
    }
    
	@Override
	public PasienModel[] getAllPasien() throws IOException {
		String path = "http://si-appointment.herokuapp.com/api/4/getAllPasienIGD";
		String allpasien= restTemplate.getForObject(path, String.class);
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node = mapper.readTree(allpasien);
    	JsonNode result = node.get("result");
    	PasienModel[] listpasien = mapper.treeToValue(result, PasienModel[].class);
		return listpasien;
	}
	
	@Override
	public PasienModel getPasien(String id) throws IOException {
		String path = "http://si-appointment.herokuapp.com/api/getPasien/" + id;
		String pasien= restTemplate.getForObject(path, String.class);
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node = mapper.readTree(pasien);
    	JsonNode result = node.get("result");
    	PasienModel pas = mapper.treeToValue(result, PasienModel.class);
    	return pas;
	}

}