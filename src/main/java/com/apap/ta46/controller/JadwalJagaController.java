package com.apap.ta46.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.ta46.model.JadwalJagaModel;
import com.apap.ta46.service.DokterService;
import com.apap.ta46.service.JadwalJagaService;

@Controller
@RequestMapping("/jadwal-jaga")
public class JadwalJagaController {
	@Autowired
	JadwalJagaService jadwalJagaService;
	
	@Autowired
	DokterService dokterService;
	
	@RequestMapping("")
	public String viewAllJadwalJaga(Model model) {
		List<JadwalJagaModel> listAllJadwalJaga = jadwalJagaService.getAllJadwalJaga();
		model.addAttribute("listAllJadwalJaga", listAllJadwalJaga);
		return "jadwal-jaga";
	}
}
