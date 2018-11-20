package com.apap.ta46.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.service.KamarService;
import com.apap.ta46.service.PaviliunService;

@Controller
@RequestMapping("/penanganan")
public class PenangananController {
	
	@Autowired 
	PaviliunService paviliunService;
	
	@Autowired
	KamarService kamarService;
	
	@RequestMapping(value = "")
	public String viewPasienRawatInap(Model model) {
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		return "list-pasien-rawat-inap";
	}
	
	@RequestMapping(value = "", params= {"idPaviliun"})
	private String viewPasienRawatInapSubmit(@RequestParam(value = "idPaviliun") long idPaviliun, Model model)  throws IOException {
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		PaviliunModel paviliunSelected = paviliunService.getPaviliun(idPaviliun); 
		model.addAttribute("paviliunSelected", paviliunSelected);
		
		List<KamarModel> listKamar = paviliunService.getPaviliun(idPaviliun).getKamarList(); //list kamar dari paviliun yang telah dipilih
		model.addAttribute("listKamar", listKamar);
		
		return "list-pasien-rawat-inap";
	}
	
}
