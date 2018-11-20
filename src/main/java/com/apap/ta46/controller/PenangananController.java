package com.apap.ta46.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.rest.Setting;
import com.apap.ta46.service.KamarService;
import com.apap.ta46.service.PaviliunService;
import com.apap.ta46.service.PenangananService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/penanganan")
public class PenangananController {
	
	@Autowired 
	PaviliunService paviliunService;
	
	@Autowired
	KamarService kamarService;
	
	@Autowired
	PenangananService penangananService;
	
	@Autowired
	RestTemplate restTemplate;
	
    @Bean
    public RestTemplate rest() {
    	return new RestTemplate();
    }
	
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
	
	@RequestMapping("/{idPasien}")
	private String viewDetailPasien(@PathVariable(value="idPasien") long idPasien, Model model) throws IOException {
		model.addAttribute("idPasien", idPasien);

		String path = Setting.pasienUrl + idPasien;
		PasienModel pasien = null;
		String stringPasien = restTemplate.getForObject(path, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(stringPasien);
		JsonNode result = node.get("result");
		pasien = mapper.treeToValue(result, PasienModel.class);
		model.addAttribute("pasien", pasien);
		
		List<PemeriksaanModel> listPenanganan = penangananService.getPenangananByIdPasien(idPasien);
		
		String statusPenanganan = null;
		
		if (listPenanganan.isEmpty()) {
			statusPenanganan = "kosong";
		}
		else {
			statusPenanganan = "ada";
			model.addAttribute("listPenanganan", listPenanganan);
		}
		model.addAttribute("statusPenanganan", statusPenanganan);
		
		return "detail-pasien";
	}
}
