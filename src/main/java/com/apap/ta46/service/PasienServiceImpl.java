package com.apap.ta46.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.PasienModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public PasienModel getPasien(String id) throws IOException {
		String path = "http://si-appointment.herokuapp.com/api/getPasien/" + id;
		String pasien= restTemplate.getForEntity(path, String.class).getBody();
		    	
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(pasien);
		JsonNode result = node.get("result");
		    	
		System.out.println(pasien);
		System.out.println(result.get("statusPasien").get("jenis"));
		PasienModel pas = mapper.treeToValue(result, PasienModel.class);
		System.out.println(pas);
		return pas;
	}
    
	//@Override
	//public PasienModel getPasien(String id) throws IOException {
		//String path = "http://si-appointment.herokuapp.com/api/getPasien/" + id;
		//String pasien= restTemplate.getForObject(path, String.class);
    	
	//	ObjectMapper mapper = new ObjectMapper();
    	//JsonNode node = mapper.readTree(pasien);
    	//JsonNode result = node.get("result");
    	
   /// 	System.out.println(pasien);
    	//System.out.println(result.get("statusPasien").get("jenis"));
    	//PasienModel pas = mapper.treeToValue(result, PasienModel.class);
    	//System.out.println(pas);
	//}

}
