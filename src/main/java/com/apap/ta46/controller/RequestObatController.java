package com.apap.ta46.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.PemeriksaanModel;
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
	
	@GetMapping(value="/obat/request/{idPemeriksaan}")
	private String addObat(@PathVariable(value = "idPemeriksaan") String idPemeriksaan, Model model) {
		PemeriksaanModel pemeriksaan = new PemeriksaanModel();
		PemeriksaanModel archive = pemeriksaanService.getPemeriksaan(Long.parseLong(idPemeriksaan));
		model.addAttribute("cek", archive);
		pemeriksaan.setIdPasien(archive.getIdPasien());
		pemeriksaan.setRequestObatList(new ArrayList<RequestObatModel>());
		pemeriksaan.getRequestObatList().add(new RequestObatModel());
		model.addAttribute("pemeriksaan", pemeriksaan);
		model.addAttribute("idPemeriksaan", idPemeriksaan);
		return "minta-obat";
	}
	
	@PostMapping(value="/obat/request/{idPemeriksaan}", params={"addRow"})
	private String addRowObat(@PathVariable(value = "idPemeriksaan") String idPemeriksaan,@ModelAttribute PemeriksaanModel pemeriksaan, Model model) {
		pemeriksaan.getRequestObatList().add(new RequestObatModel());
		PemeriksaanModel archive = pemeriksaanService.getPemeriksaan(Long.parseLong(idPemeriksaan));
		model.addAttribute("idPasien", archive.getIdPasien());
		model.addAttribute("pemeriksaan", pemeriksaan);
		model.addAttribute("idPemeriksaan", idPemeriksaan);
		return "minta-obat";
	}
	
	@PostMapping(value="/obat/request/{idPemeriksaan}", params={"removeRow"})
	private String removeRowObat(@PathVariable(value = "idPemeriksaan") String idPemeriksaan, @ModelAttribute PemeriksaanModel pemeriksaan, Model model, final HttpServletRequest req) {
		Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		pemeriksaan.getRequestObatList().remove(rowId.intValue());
		model.addAttribute("pemeriksaan", pemeriksaan);
		model.addAttribute("idPemeriksaan", idPemeriksaan);
		return "minta-obat";
	}
	
	@PostMapping(value="/obat/request/{idPemeriksaan}", params={"save"})
	private String saveObat(@PathVariable(value = "idPemeriksaan") String idPemeriksaan, @ModelAttribute PemeriksaanModel pemeriksaan, Model model) {
		for(RequestObatModel pem: pemeriksaan.getRequestObatList()) {
			 HttpEntity<RequestObatModel> entity = new HttpEntity<RequestObatModel>(pem);
			 RestTemplate restTemplate = new RestTemplate();
			 String response ="";
			 try {
				 ResponseEntity<String> obatEntity = restTemplate.exchange("https://335d9e5c-f224-4922-ad16-1388bfe9068d.mock.pstmn.io/obat", HttpMethod.POST, entity, String.class);
				 response = obatEntity.getBody();
				 
			 } catch (Exception e) {
				 response = e.getMessage();
			 }
			 System.out.println(response);
		}
		return "redirect:/";
	}
	
	@GetMapping(value="/obat/service")
	private String simpanObat(@PathVariable(value = "idPemeriksaan") String idObat) {
		 RequestObatModel pem = requestObatService.findObatById(1);
		 HttpEntity<RequestObatModel> entity = new HttpEntity<RequestObatModel>(pem);
		 RestTemplate restTemplate = new RestTemplate();
		 String response = "";
		 try {
			 ResponseEntity<String> obatEntity = restTemplate.exchange("https://335d9e5c-f224-4922-ad16-1388bfe9068d.mock.pstmn.io/obat", HttpMethod.POST, entity, String.class);
			 response = obatEntity.getBody();
		 } catch (Exception e){
			 response = e.getMessage(); 
		 }
		 System.out.println(response);
		 return "redirect:/";
	}
}
