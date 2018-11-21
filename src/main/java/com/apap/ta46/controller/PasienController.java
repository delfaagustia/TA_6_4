package com.apap.ta46.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.Response;

@Controller
public class PasienController {
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restPasien() {
		return new RestTemplate();
	}
	
	@GetMapping(value="/view/{id}")
	public void getPasien(@PathVariable("id") String id) 
			throws Exception {
		String path = "http://si-appointment.herokuapp.com/api/getPasien/" + id;
		Response pasien = restTemplate.getForObject(path, Response.class);
		System.out.println(pasien.toString());
		
		
	}
	
}
