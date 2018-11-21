package com.apap.ta46.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.apap.ta46.model.DokterModel;
import com.apap.ta46.model.JadwalJagaModel;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.rest.Setting;
import com.apap.ta46.service.JadwalJagaService;
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
	JadwalJagaService jadwalJagaService;
	
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

		PasienModel pasien = getPasienById(idPasien);
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

		model.addAttribute("kamar", kamarService.getKamarByIdPasien(idPasien).getId());
		model.addAttribute("paviliun", kamarService.getKamarByIdPasien(idPasien).getPaviliun().getNamaPaviliun());
		
		return "detail-pasien";
	}
	
	@RequestMapping("/{idPasien}/insert")
	private String addPenanganan(@PathVariable(value="idPasien") long idPasien, Model model) throws IOException {
		List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getAllJadwalJaga();
		Set<DokterModel> listDokter = new HashSet<DokterModel>();
		
		for (JadwalJagaModel jadwal: listJadwalJaga) {
			listDokter.add(getDokterById(jadwal.getIdDokter()));
		}
		
		model.addAttribute("listDokter", listDokter);
		model.addAttribute("pasien", getPasienById(idPasien));
		return "add-penanganan";
	}
	
	@RequestMapping(value = "/{idPasien}/success")
	private String addPenangananSubmit(@ModelAttribute PemeriksaanModel penanganan, 
									   @PathVariable(value="idPasien") long idPasien,
									   @RequestParam("waktuFix") String waktuFix, Model model) throws IOException, ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate = dateFormat.parse(waktuFix.replace("T", " "));
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		penanganan.setWaktu(timestamp);
		
		penangananService.add(penanganan);
		
		PasienModel pasien = getPasienById(idPasien);
		model.addAttribute("pasien", pasien);
		
		List<PemeriksaanModel> listPenanganan = penangananService.getPenangananByIdPasien(idPasien);
		model.addAttribute("listPenanganan", listPenanganan);
		return "detail-pasien";
	}
	
	//method yang mengembalikan object Dokter dari API
	private DokterModel getDokterById(Long idDokter) throws IOException {
		String path = Setting.dokterUrl + idDokter;
		String stringDokter = restTemplate.getForObject(path, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(stringDokter);
		JsonNode result = node.get("result");
		DokterModel dokter = mapper.treeToValue(result, DokterModel.class);
		return dokter;
	}
	//method yang mengembalikan object Pasien dari API
	private PasienModel getPasienById(Long idPasien) throws IOException {
		String path = Setting.pasienUrl + idPasien;
		String stringPasien = restTemplate.getForObject(path, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(stringPasien);
		JsonNode result = node.get("result");
		PasienModel pasien = mapper.treeToValue(result, PasienModel.class);
		return pasien;
	}
	
}
