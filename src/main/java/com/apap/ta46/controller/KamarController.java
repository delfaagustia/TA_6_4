package com.apap.ta46.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.service.KamarService;
import com.apap.ta46.service.PasienService;
import com.apap.ta46.service.PaviliunService;

@Controller
public class KamarController {
	@Autowired
	private PaviliunService paviliunService;
	
	@Autowired
	private PasienService pasienService;
	
	@Autowired
	private KamarService kamarService;
	
	@GetMapping(value="/addkamar")
	private String addKamar(Model model) {
		KamarModel kamar = new KamarModel();
		model.addAttribute("kamar", kamar);
		model.addAttribute("listPaviliun", paviliunService.getAllPaviliun());
		return "addKamar";
	}
	
	@PostMapping(value="/addkamar")
	private String addKamarSubmit(@ModelAttribute KamarModel kamar) {
		kamarService.addKamar(kamar);
		return "sukses";
	}
	
	@GetMapping(value="/updatekamar/{id}")
	private String updateKamar(@PathVariable("id") String id, Model model) throws IOException {
		PasienModel[] pas = pasienService.getAllPasien();
		KamarModel kamar = kamarService.getKamar(Long.parseLong(id));
		model.addAttribute("kamar", kamar);
		model.addAttribute("listPaviliun", paviliunService.getAllPaviliun());
		model.addAttribute("listPasien", pas);
		return "updateKamar";
	}
	
	@PostMapping(value="/updatekamar")
	private String updateKamarSubmit(@ModelAttribute KamarModel kamar, Model model){
		kamarService.updateKamar(kamar);
		return "sukses";
	}
	
//	@GetMapping(value="/getallpasien")
//	private void getAllPasien() throws IOException {
//		PasienModel[] pas = pasienService.getAllPasien();
//		System.out.println(pas);
//	}
//	
//	@GetMapping(value="/pasien/{id}")
//	private void getPasien(@PathVariable("id") String id) throws IOException {
//		pasienService.getPasien(id);
//	}
	
}
