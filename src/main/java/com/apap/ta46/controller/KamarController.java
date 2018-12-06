package com.apap.ta46.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.RequestPasienModel;
import com.apap.ta46.service.KamarService;
import com.apap.ta46.service.PasienService;
import com.apap.ta46.service.PaviliunService;
import com.apap.ta46.service.RequestPasienService;

@Controller
public class KamarController {
	@Autowired
	private KamarService kamarService;
	
	@Autowired
	private PaviliunService paviliunService;
	
	@Autowired
	private PasienService pasienService;
	
	@Autowired
	private RequestPasienService requestPasienService;
	
	@RequestMapping(value="/kamar", method=RequestMethod.GET)
	private String findKamar(@RequestParam(value="idPaviliun", required = false)
	Optional<String> idPaviliun, @RequestParam(value="status", required = false) 
	Optional<String> statusKamar, Model model) {
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		
		List<KamarModel> kamarResult = new ArrayList<>();
		
		if(idPaviliun.isPresent()) {
			kamarResult = kamarService.findKamarByPaviliun(Long.parseLong(idPaviliun.get()));
			if (statusKamar.isPresent()) {
				kamarResult = kamarService.findKamarByPaviliunAndStatus(Long.parseLong(idPaviliun.get()), 
						Integer.parseInt(statusKamar.get()));
			}
		}
		else {
			if(statusKamar.isPresent()) {
				kamarResult = kamarService.findKamarByStatus(Integer.parseInt(statusKamar.get()));
			}
		}
		
		model.addAttribute("listKamar", kamarResult);
		
		return "findKamar";
	}
	
	@RequestMapping(value = "/kamar/{id}", method = RequestMethod.GET)
	private String viewDetailKamar(@PathVariable(value="id") long id, Model model){
		KamarModel kamar = kamarService.getKamar(id);
		model.addAttribute("kamar", kamar);
		
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		
		return "detailKamar";
	}

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
	
	//HALOOO SEMENTARA GUA TARO SINI YA, SOALNYA BERHUBUNGAN BANGET SAMA KAMAR NIH//
	@RequestMapping(value = "/daftar-ranap")
	private String viewAllPasienRanap(Model model) throws IOException {
		List<KamarModel> kamarList = kamarService.findKamarByStatus(1);
		
		Map<KamarModel, PasienModel> map = new HashMap<>();
		
		for(KamarModel kamar : kamarList) {
			System.out.println(kamar.getStatus());
			String idPasien = String.valueOf(kamar.getIdPasien());
			
			map.put(kamar, pasienService.getPasien(idPasien));
		}
		
		model.addAttribute("map", map);
		model.addAttribute("kamarList", kamarList);
		return "daftar-ranap";
	}
	
	@RequestMapping(value="/daftar-ranap/pulang/{idKamar}")
	private String kosongkanKamar(@PathVariable("idKamar") String idKamar, Model model) throws IOException {
		
		KamarModel kamar = kamarService.getKamar(Long.parseLong(idKamar));
		
		RequestPasienModel req = requestPasienService.getRequestPasienByIdPasien(kamar.getIdPasien());
		req.setAssign(0);
		requestPasienService.updateRequestPasien(req);
		
		kamar.setIdPasien(0);
		kamar.setStatus(0);
		kamarService.updateKamar(kamar);
		
		return "sukses";
	}
	
//	@GetMapping(value="/getallpasien")
//	private void getAllPasien() throws IOException {
//		PasienModel[] pas = pasienService.getAllPasien();
//		System.out.println(pas);
//	}

//	@GetMapping(value="/pasien/{id}")
//	private void getPasien(@PathVariable("id") String id) throws IOException {
//		pasienService.getPasien(id);
//	}
	
}
