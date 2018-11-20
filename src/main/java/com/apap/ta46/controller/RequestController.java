package com.apap.ta46.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.rest.Setting;

@RestController
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
	@GetMapping(value = "/")
	public String viewAllRequest(){
		String path = Setting.pasienUrl;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
}
