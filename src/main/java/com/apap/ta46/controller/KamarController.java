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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value = "/kamar/{id}", method = RequestMethod.GET)
	private String viewDetailKamar(@PathVariable(value="id") long id, Model model) throws IOException{
		KamarModel kamar = kamarService.getKamar(id);
		System.out.println(kamar.getPaviliun().getNamaPaviliun());
		model.addAttribute("kamar", kamar);
		PasienModel pasien = pasienService.getPasien(String.valueOf(kamar.getIdPasien()));
		model.addAttribute("pasien", pasien);
		return "detailKamar";
	}

	@GetMapping(value="/kamar")
	private String addKamar(Model model) {
		KamarModel kamar = new KamarModel();
		model.addAttribute("kamar", kamar);
		model.addAttribute("listPaviliun", paviliunService.getAllPaviliun());
		model.addAttribute("listKamar", kamarService.getAllKamar());
		return "addKamar";
	}
	
	@PostMapping(value="/kamar/insert")
	private String addKamarSubmit(@ModelAttribute KamarModel kamar, Model model, RedirectAttributes redirectAttr) {
		if(kamar.getPaviliun() == null) {
			redirectAttr.addFlashAttribute("message", "Kamar Tidak Berhasil ditambahkan");
			redirectAttr.addFlashAttribute("alert", 0);
		}else {
			kamarService.addKamar(kamar);
			redirectAttr.addFlashAttribute("message", "Kamar Berhasil ditambahkan");
			redirectAttr.addFlashAttribute("alert", 1);
		}
		return "redirect:/kamar";
	}
	
	@GetMapping(value="/updatekamar/{id}")
	private String updateKamar(@PathVariable("id") String id, Model model) throws IOException {
		List<PasienModel> pas = pasienService.getAllPasienRawatInap();
		KamarModel kamar = kamarService.getKamar(Long.parseLong(id));
		model.addAttribute("kamar", kamar);
		model.addAttribute("listPaviliun", paviliunService.getAllPaviliun());
		model.addAttribute("listPasien", pas);
		return "updateKamar";
	}
	
	@PostMapping(value="/kamar/{id}")
	private String updateKamarSubmit(@PathVariable("id") String id, @ModelAttribute KamarModel kamar, RedirectAttributes redirectAttr){
		if((kamar.getIdPasien() != 0 && kamar.getStatus() == 0) || (kamar.getIdPasien() == 0 && kamar.getStatus() == 1)) {
			redirectAttr.addFlashAttribute("message", "Kamar Tidak Berhasil diupdate");
			redirectAttr.addFlashAttribute("alert", 0);
		} else {
			kamarService.updateKamar(Long.parseLong(id), kamar);
			redirectAttr.addFlashAttribute("message", "Kamar Berhasil diupdate");
			redirectAttr.addFlashAttribute("alert", 1);
		}
		return "redirect:/kamar";
	}
}
