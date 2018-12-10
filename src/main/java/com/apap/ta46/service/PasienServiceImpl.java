package com.apap.ta46.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.RequestPasienModel;
import com.apap.ta46.rest.BaseResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {
	@Autowired
	RequestPasienService requestPasienService;
	
	@Autowired
    RestTemplate restTemplate;
    
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
	
	@Override
	public BaseResponse postPasien(PasienModel pasien) {
		BaseResponse response = null;
		
		HttpEntity<PasienModel> entity = new HttpEntity<PasienModel>(pasien);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BaseResponse> pasienEntity = restTemplate.exchange("http://si-appointment.herokuapp.com/api/4/updatePasien", HttpMethod.POST, entity, BaseResponse.class);
		response = pasienEntity.getBody();
			
		return response;
	}

	@Override
	public List<PasienModel> getAllPasienRawatInap() throws IOException {
		List<RequestPasienModel> pasienAntrian = requestPasienService.getAllRequestPasien();
		ArrayList<PasienModel> listPasien = new ArrayList<>();
		for (RequestPasienModel pas : pasienAntrian) {
			listPasien.add(this.getPasien(String.valueOf(pas.getIdPasien())));
		}
		return listPasien;
	}

}