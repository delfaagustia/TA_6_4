package com.apap.ta46.controller;

import java.io.IOException;  
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.apap.ta46.model.DokterModel;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.service.DokterService;
import com.apap.ta46.service.JadwalJagaService;
import com.apap.ta46.service.KamarService;
import com.apap.ta46.service.PasienService;
import com.apap.ta46.service.PaviliunService;
import com.apap.ta46.service.PenangananService;

@Controller
@RequestMapping("/pasien-ranap")
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
	PasienService pasienService;
	
	@Autowired
	DokterService dokterService;
	
	@RequestMapping(value = "")
	public String viewPasienRawatInap(Model model) {
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		return "list-pasien-rawat-inap";
	}
	

	@RequestMapping(value= "/all")
	private String viewAllPasienRawatInapSubmit(Model model) throws IOException {
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		
		List<KamarModel> listKamarFix = new ArrayList<>();
		
		for (PaviliunModel paviliun: listPaviliun) {
			for (KamarModel kamar: paviliun.getKamarList()) {
				if (kamar.getStatus() == 1) {
					listKamarFix.add(kamar);;
				}
			}
		}
		
		Map<KamarModel, PasienModel> map = new HashMap<>();
		for(KamarModel kamar : listKamarFix) {
			String idPasien = Long.toString(kamar.getIdPasien());
			map.put(kamar, pasienService.getPasien(idPasien));
		}
		
		model.addAttribute("map", map);
		model.addAttribute("listKamar", listKamarFix);
		
		return "list-pasien-rawat-inap";
	}
	
	@RequestMapping(value = "", params= {"idPaviliun"})
	private String viewPasienRawatInapSubmit(@RequestParam(value = "idPaviliun") long idPaviliun, Model model)  throws IOException {
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		
		PaviliunModel paviliunSelected = paviliunService.getPaviliun(idPaviliun); 
		model.addAttribute("paviliunSelected", paviliunSelected);
		
		List<KamarModel> listKamar = paviliunService.getPaviliun(idPaviliun).getKamarList(); //list kamar dari paviliun yang telah dipilih
		List<KamarModel> listKamarFix = new ArrayList<>();
		
		for (KamarModel kamar: listKamar) {
			if (kamar.getStatus() == 1) {
				listKamarFix.add(kamar);
			}
		}
		
		Map<KamarModel, PasienModel> map = new HashMap<>();
		for(KamarModel kamar : listKamarFix) {
			String idPasien = Long.toString(kamar.getIdPasien());
			map.put(kamar, pasienService.getPasien(idPasien));
		}
		
		model.addAttribute("map", map);
		model.addAttribute("listKamar", listKamarFix);
		
		return "list-pasien-rawat-inap";
	}
	
	@RequestMapping("/{idPasien}")
	private String viewDetailPasien(@PathVariable(value="idPasien") long idPasien, Model model) throws IOException {
		PasienModel pasien = pasienService.getPasien(Long.toString(idPasien));
		model.addAttribute("pasien", pasien);
		
		List<PemeriksaanModel> listPenanganan = penangananService.getPenangananByIdPasien(idPasien);
		
		String statusPenanganan = null;
		
		if (listPenanganan.isEmpty()) {
			statusPenanganan = "empty";
		}
		else {
			statusPenanganan = "exist";
			model.addAttribute("listPenanganan", listPenanganan);
		}
		
		model.addAttribute("statusPenanganan", statusPenanganan);

		model.addAttribute("kamar", kamarService.getKamarByIdPasien(idPasien).getId());
		model.addAttribute("paviliun", kamarService.getKamarByIdPasien(idPasien).getPaviliun().getNamaPaviliun());
		
		return "detail-pasien";
	}
	
	/**
	 * [6] Melakukan Insert Data Penanganan yang Telah Dilakukan
	 * @param idPasien
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/{idPasien}/insert")
	private String addPenanganan(@PathVariable(value="idPasien") long idPasien, Model model) throws IOException {
		model.addAttribute("listDokter", dokterService.getAllDokter());
		model.addAttribute("pasien", pasienService.getPasien(Long.toString(idPasien)));
		return "add-penanganan";
	}
	
	@RequestMapping("/{idPasien}/success")
	private String addPenangananSubmit(@ModelAttribute PemeriksaanModel penanganan, 
									   @PathVariable(value="idPasien") long idPasien,
									   @RequestParam("waktuFix") String waktuFix, Model model) throws IOException, ParseException {
		penanganan.setWaktu(stringToTimestamp(waktuFix));
		penangananService.add(penanganan);
		
		PasienModel pasien = pasienService.getPasien(Long.toString(idPasien));
		model.addAttribute("pasien", pasien);
		
		List<PemeriksaanModel> listPenanganan = penangananService.getPenangananByIdPasien(idPasien);
		model.addAttribute("listPenanganan", listPenanganan);
		
		model.addAttribute("kamar", kamarService.getKamarByIdPasien(idPasien).getId());
		model.addAttribute("paviliun", kamarService.getKamarByIdPasien(idPasien).getPaviliun().getNamaPaviliun());
		
		model.addAttribute("statusPenanganan", "exist");
		return "detail-pasien";
	}
	
	/**
	 * [7] Melihat Data Penanganan yang Telah Dilakukan
	 * @param idPasien
	 * @param idPenanganan
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/{idPasien}/penanganan/{idPenanganan}")
	private String viewPenanganan(@PathVariable(value="idPasien") long idPasien,
								  @PathVariable(value="idPenanganan") long idPenanganan, Model model) throws IOException{
		PemeriksaanModel penanganan = penangananService.getPenangananById(idPenanganan);
		model.addAttribute("penanganan", penanganan);

		DokterModel dokter = dokterService.getDokterById(penanganan.getIdDokter());
		model.addAttribute("dokter", dokter);

		return "detail-penanganan";
	}
	
	/**
	 * [8] Mengubah Data Penanganan yang Telah Dilakukan
	 * @param idPasien
	 * @param idPenanganan
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/{idPasien}/ubah-penanganan/{idPenanganan}")
	private String updatePenanganan(@PathVariable(value="idPasien") long idPasien,
									@PathVariable(value="idPenanganan") long idPenanganan, Model model) throws IOException {
		PasienModel pasien = pasienService.getPasien(Long.toString(idPasien));
		model.addAttribute("pasien", pasien);

		PemeriksaanModel penanganan = penangananService.getPenangananById(idPenanganan);
		model.addAttribute("penanganan", penanganan);
		
		DokterModel dokter = dokterService.getDokterById(penanganan.getIdDokter());
		model.addAttribute("dokterSelected", dokter);
		
		String waktu = penanganan.getWaktu().toString().replaceAll(" ", "T");
		model.addAttribute("waktu", waktu);
		
		model.addAttribute("listDokter", dokterService.getAllDokter());
		
		return "update-penanganan";
	}
	
	@RequestMapping("/{idPasien}/lihat-penanganan/{idPenanganan}/success")
	private String updatePenangananSubmit(@ModelAttribute PemeriksaanModel penanganan, 
										  @PathVariable(value="idPasien") long idPasien,
										  @PathVariable(value="idPenanganan") long idPenanganan,
										  @RequestParam("waktuFix") String waktuFix, Model model) throws IOException, ParseException  {
		penanganan.setWaktu(stringToTimestamp(waktuFix));
		penangananService.add(penanganan);

		model.addAttribute("penanganan", penanganan);

		DokterModel dokter = dokterService.getDokterById(penanganan.getIdDokter());
		model.addAttribute("dokter", dokter);
		
		return "detail-penanganan";
	}
	
	//method yang merubah tipe data String menjadi Timestamp, untuk kebutuhan input waktu dari form ke object penanganan 
	private Timestamp stringToTimestamp(String waktu) throws ParseException {
		waktu = waktu.replace("T", " ");
		if (waktu.length()==16) { //kalau user isi second :00
			waktu +=":00";
		} 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate = dateFormat.parse(waktu.replace("T", " "));
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    return timestamp;
	}
}
