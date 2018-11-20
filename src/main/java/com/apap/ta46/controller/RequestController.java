package com.apap.ta46.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.StatusPasienModel;
import com.apap.ta46.rest.Setting;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/daftar-request")
public class RequestController {
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
    public RestTemplate rest() {
    	return new RestTemplate();
    }
	
	/**
	 * GET all request 
	 */
	@RequestMapping(value = "/")
	public String viewAllRequest(Model model) throws IOException{
		String path = Setting.pasienUrl;
		
		String allPasien = restTemplate.getForObject(path, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(allPasien);
		JsonNode result = node.get("result");
		
		PasienModel[] pasienList = mapper.treeToValue(result, PasienModel[].class);
		
		System.out.println(result.get(1).get("statusPasien").get("id").asLong());
		
		int counter=0;
		for(PasienModel pasien : pasienList) {
			StatusPasienModel status = new StatusPasienModel();
			
			long idStatus = result.get(counter).get("statusPasien").get("id").asLong();
			status.setId(idStatus);
			
			String jenis = result.get(counter).get("statusPasien").get("jenis").asText();
			status.setJenis(jenis);
			
			pasien.setStatus(status);
			counter++;
		}
		
		
		
		model.addAttribute("pasienList", pasienList);
		return "request";
	}
}
