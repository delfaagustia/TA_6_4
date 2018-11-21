package com.apap.ta46.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.service.KamarService;
import com.apap.ta46.service.PaviliunService;

@Controller
public class KamarController {
	@Autowired
	private KamarService kamarService;
	
	@Autowired
	private PaviliunService paviliunService;
	
	@RequestMapping(value="/kamar", method=RequestMethod.GET)
	private String findKamar(@RequestParam(value="idPaviliun", required = false)
	Optional<String> idPaviliun, @RequestParam(value="status", required = false) 
	Optional<String> statusKamar, Model model) {
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		
		List<KamarModel> kamarResult = new ArrayList<>();
		
		if(idPaviliun.isPresent()) {
			kamarResult = kamarService.findKamarByPaviliun(Long.parseLong(idPaviliun.get()));
		}
		
		model.addAttribute("listKamar", kamarResult);
		
		return "findKamar";
	}
	
	@RequestMapping(value = "/kamar/{id}", method = RequestMethod.GET)
	private String viewDetailKamar(@PathVariable(value="id") long id, Model model){
		KamarModel kamar = kamarService.getKamarDetailById(id);
		model.addAttribute("kamar", kamar);
		return "detailKamar";
	}
}
