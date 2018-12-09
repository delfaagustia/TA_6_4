package com.apap.ta46.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.RequestPasienModel;
import com.apap.ta46.model.StatusPasienModel;
import com.apap.ta46.service.KamarService;
import com.apap.ta46.service.PasienService;
import com.apap.ta46.service.PaviliunService;
import com.apap.ta46.service.RequestPasienService;

@Controller
public class RequestPasienController {
	@Autowired
	RequestPasienService requestPasienService;
	
	@Autowired
	PasienService pasienService;
	
	@Autowired
	KamarService kamarService;
	
	@Autowired
	PaviliunService paviliunService;
		
	/**
	 * GET all request pasien
	 * @throws IOException 
	 */
	@RequestMapping(value = "/daftar-request")
	private String viewAllRequest(Model model) throws IOException {
		List<RequestPasienModel> requestPasienList = requestPasienService.getAllRequestPasien();
		
		Map<RequestPasienModel, PasienModel> map = new HashMap<>();
		
		for(RequestPasienModel requestPasien : requestPasienList) {
			String idPasien = String.valueOf(requestPasien.getIdPasien());
			
			map.put(requestPasien, pasienService.getPasien(idPasien));
		}
		
		model.addAttribute("map", map);
		model.addAttribute("requestPasienList", requestPasienList);
		return "request";
	}
	
	@RequestMapping(value="/daftar-request/assign/{idPasien}", method = RequestMethod.GET)
	private String assignKamarPasien(@PathVariable(value="idPasien") String idPasien, Model model) throws IOException {
		PasienModel pasien = pasienService.getPasien(idPasien);
		model.addAttribute("pasien", pasien);
		
		List<PaviliunModel> paviliunList = paviliunService.getAllPaviliun();
		
		model.addAttribute("paviliunList", paviliunList);
		
		return "request-assign-kamar";
	}
	
	@RequestMapping(value="/daftar-request/assign", method = RequestMethod.POST)
	private String assignKamarPasienSubmit(@ModelAttribute KamarModel kamarPalsu, Model model) throws IOException {
		KamarModel kamarAsli = kamarService.getKamar(kamarPalsu.getId());
		
		RequestPasienModel rp = requestPasienService.getRequestPasienByIdPasien(kamarPalsu.getIdPasien());
		rp.setAssign(1);
		
		kamarAsli.setIdPasien(kamarPalsu.getIdPasien());
		kamarAsli.setStatus(1);
		
		//ambil pasien buat di post ke api appointment
		PasienModel pasien = pasienService.getPasien(String.valueOf(rp.getIdPasien()));
		StatusPasienModel status = pasien.getStatusPasien();
		status.setId(5);
		status.setJenis("Berada di Rawat Inap");
		pasien.setStatusPasien(status);
		pasienService.postPasien(pasien);
		
		kamarService.addKamar(kamarAsli);
		return this.viewAllPasienRanap(model);
	}
	
	//HALOOO SEMENTARA GUA TARO SINI YA, SOALNYA BERHUBUNGAN BANGET SAMA KAMAR NIH//
		@RequestMapping(value = "/daftar-ranap")
		private String viewAllPasienRanap(Model model) throws IOException {
			List<KamarModel> kamarList = kamarService.getAllKamarByStatus(1);
			
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
			
			//ambil pasien buat di post ke api appointment
			PasienModel pasien = pasienService.getPasien(String.valueOf(kamar.getIdPasien()));
			StatusPasienModel status = pasien.getStatusPasien();
			status.setId(6);
			status.setJenis("Selesai di Rawat Inap");
			pasien.setStatusPasien(status);
			pasienService.postPasien(pasien);
			
			//di request pasiennya jadi 0
			RequestPasienModel req = requestPasienService.getRequestPasienByIdPasien(kamar.getIdPasien());
			req.setAssign(2);
			requestPasienService.updateRequestPasien(req);
			
			//di kamar pasiennya jadi 0 statusnya juga
			kamar.setIdPasien(0);
			kamar.setStatus(0);
			kamarService.addKamar(kamar);
			
			return this.viewAllPasienRanap(model);
		}
		
		@RequestMapping(value="/paviliun-available", method=RequestMethod.GET)
		public @ResponseBody List<KamarModel> getAllPaviliunAvailable(@RequestParam(value="idPaviliun") long idPaviliun){
			System.out.println(paviliunService.getKamarAvailable(idPaviliun).get(0).getId());
			return paviliunService.getKamarAvailable(idPaviliun);
		}
	
	
}
