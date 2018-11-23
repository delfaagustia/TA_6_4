package com.apap.ta46.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.apap.ta46.service.PemeriksaanService;

@Controller
public class RequestObatController {
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	@GetMapping(value="/obat/request/")
	private String addObat(Model model) {
		model.addAttribute("listPemeriksaan", pemeriksaanService.getAllPemeriksaan());
		return "requestobat";
	}
}
