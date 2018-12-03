package com.apap.ta46.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.apap.ta46.model.RequestObatModel;
import com.apap.ta46.rest.BaseResponse;
import com.apap.ta46.service.PemeriksaanService;
import com.apap.ta46.service.RequestObatService;

@Controller
public class RequestObatController {
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	@Autowired
	private RequestObatService requestObatService;
	
	@GetMapping(value="/obat/request")

	private String addObat(Model model) {
		model.addAttribute("listPemeriksaan", pemeriksaanService.getAllPemeriksaan());
		return "requestobat";
	}
	
	@PostMapping(value="/obat/request")
	public String requestObat(@ModelAttribute RequestObatModel obat) {
		BaseResponse<RequestObatModel> response = requestObatService.addObat(obat);
		if(response.getStatus() == 200) {
			System.out.println(response);
		}
		return "requestobat";
	}
}
