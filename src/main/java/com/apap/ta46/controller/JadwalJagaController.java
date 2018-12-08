package com.apap.ta46.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.ta46.model.DokterModel;
import com.apap.ta46.model.JadwalJagaModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.RequestPasienModel;
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
	public String viewAllJadwalJaga(Model model) throws IOException {
		List<JadwalJagaModel> listAllJadwalJaga = jadwalJagaService.getAllJadwalJaga();
		
		Map<JadwalJagaModel, DokterModel> map = new HashMap<>();
		
		for(JadwalJagaModel jadwalJaga : listAllJadwalJaga) {
			long idDokter = jadwalJaga.getIdDokter();
			
			map.put(jadwalJaga, dokterService.getDokterById(idDokter));
		}
		
		model.addAttribute("map", map);
		
		model.addAttribute("listAllJadwalJaga", listAllJadwalJaga);
		return "jadwal-jaga";
	}
	
	@RequestMapping("/dokter/{idDokter}")
	public String viewDokter(@PathVariable(value="idDokter") long idDokter, Model model) throws IOException {
		DokterModel dokter = dokterService.getDokterById(idDokter);
		model.addAttribute("dokter", dokter);
		return ("detail-dokter");
	}
}
