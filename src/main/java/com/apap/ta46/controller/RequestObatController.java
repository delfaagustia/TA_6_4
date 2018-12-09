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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		pemeriksaan.setRequestObatList(new ArrayList<RequestObatModel>());
		RequestObatModel obat = new RequestObatModel();
		obat.setIdPasien(archive.getIdPasien());
		pemeriksaan.getRequestObatList().add(obat);
		model.addAttribute("pemeriksaan", pemeriksaan);
		model.addAttribute("idPemeriksaan", idPemeriksaan);
		return "minta-obat";
	}
	
	@PostMapping(value="/obat/request/{idPemeriksaan}", params={"addRow"})
	private String addRowObat(@PathVariable(value = "idPemeriksaan") String idPemeriksaan,@ModelAttribute PemeriksaanModel pemeriksaan, Model model) {
		PemeriksaanModel archive = pemeriksaanService.getPemeriksaan(Long.parseLong(idPemeriksaan));
		RequestObatModel obat = new RequestObatModel();
		obat.setIdPasien(archive.getIdPasien());
		
		pemeriksaan.getRequestObatList().add(obat);
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
		BaseResponse response = requestObatService.postRequestObat(pemeriksaan.getRequestObatList(), Long.parseLong(idPemeriksaan));
		if(response.getStatus() == 200) {
			model.addAttribute("hasil", "Request obat berhasil");
			model.addAttribute("alert", 1);
		} else {
			model.addAttribute("hasil", "Request obat tidak berhasil");
			model.addAttribute("alert", 0);
		}
		return "sukses";
	}
}
