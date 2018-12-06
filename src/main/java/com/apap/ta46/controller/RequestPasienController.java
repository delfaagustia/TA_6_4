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
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.RequestPasienModel;
import com.apap.ta46.service.KamarService;
import com.apap.ta46.service.PasienService;
import com.apap.ta46.service.PaviliunService;
import com.apap.ta46.service.RequestPasienService;

@Controller
@RequestMapping("/daftar-request")
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
	@RequestMapping(value = "")
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
	
	@RequestMapping(value="/assign/{idPasien}", method = RequestMethod.GET)
	private String assignKamarPasien(@PathVariable(value="idPasien") String idPasien, Model model) throws IOException {
		PasienModel pasien = pasienService.getPasien(idPasien);
		model.addAttribute("pasien", pasien);
		
		List<PaviliunModel> paviliunList = paviliunService.getAllPaviliun();
		
		model.addAttribute("paviliunList", paviliunList);
		
		return "request-assign-kamar";
	}
	
	@RequestMapping(value="/assign", method = RequestMethod.POST)
	private String assignKamarPasienSubmit(@ModelAttribute KamarModel kamarPalsu, Model model) {
		KamarModel kamarAsli = kamarService.getKamar(kamarPalsu.getId());
		
		RequestPasienModel rp = requestPasienService.getRequestPasienByIdPasien(kamarPalsu.getIdPasien());
		rp.setAssign(1);
		
		kamarAsli.setIdPasien(kamarPalsu.getIdPasien());
		kamarAsli.setStatus(1);
		
		kamarService.updateKamar(kamarAsli);
		return "sukses";
	}
	
	
}
