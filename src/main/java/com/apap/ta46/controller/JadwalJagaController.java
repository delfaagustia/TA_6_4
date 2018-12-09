package com.apap.ta46.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.ta46.model.DokterModel;
import com.apap.ta46.model.JadwalJagaModel;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.RequestPasienModel;
import com.apap.ta46.model.WaktuModel;
import com.apap.ta46.service.DokterService;
import com.apap.ta46.service.JadwalJagaService;
import com.apap.ta46.service.PaviliunService;
import com.apap.ta46.service.WaktuService;

@Controller
@RequestMapping("/jadwal-jaga")
public class JadwalJagaController {
	@Autowired
	JadwalJagaService jadwalJagaService;
	
	@Autowired
	DokterService dokterService;
	
	@Autowired
	WaktuService waktuService;
	
	@Autowired
	PaviliunService paviliunService;
	
	@RequestMapping("")
	public String viewAllJadwalJaga(Model model) throws IOException {
		List<JadwalJagaModel> listAllJadwalJaga = jadwalJagaService.getAllJadwalJaga();
		
		Map<JadwalJagaModel, DokterModel> map = new HashMap<>();
		
		for(JadwalJagaModel jadwalJaga : listAllJadwalJaga) {
			long idDokter = jadwalJaga.getIdDokter();
			
			map.put(jadwalJaga, dokterService.getDokterById(idDokter));
		}
		
		model.addAttribute("map", map);
		
		model.addAttribute("listAllJadwalJaga", listAllJadwalJaga);
		return "jadwal-jaga";
	}
	
	@RequestMapping("/dokter/{idDokter}")
	public String viewDokter(@PathVariable(value="idDokter") long idDokter, Model model) throws IOException {
		DokterModel dokter = dokterService.getDokterById(idDokter);
		model.addAttribute("dokter", dokter);
		return ("detail-dokter");
	}
	
	@RequestMapping(value="/tambah", method = RequestMethod.GET)
	private String tambahJadwalJaga(Model model) throws IOException {
		JadwalJagaModel jadwalJaga = new JadwalJagaModel();
		model.addAttribute("jadwalJaga", jadwalJaga);
		
		List<WaktuModel> listWaktu = waktuService.getAllWaktu();
		model.addAttribute("listWaktu", listWaktu);
		
		DokterModel[] listDokter = dokterService.getAllDokterSIAppointment();
		model.addAttribute("listDokter", listDokter);
		
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		
		return "tambah-jadwal-jaga";
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	private String tambahJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga, Model model) throws IOException {
//		System.out.println(jadwalJaga.getStatusDokter());
//		
//		JadwalJagaModel jadwalJaga2 = new JadwalJagaModel();
//		jadwalJaga2.setIdDokter(jadwalJaga.getIdDokter());
//		jadwalJaga2.setPaviliun(jadwalJaga.getPaviliun());
//		jadwalJaga2.setStatusDokter(jadwalJaga.getStatusDokter());
//		jadwalJaga2.setWaktu(jadwalJaga.getWaktu());
		
		jadwalJagaService.add(jadwalJaga);
		
		return this.viewAllJadwalJaga(model);
	}
	
	@RequestMapping(value="/ubah/{idJadwalJaga}", method = RequestMethod.GET)
	private String ubahJadwalJaga(@PathVariable(value = "idJadwalJaga") long idJadwalJaga, Model model) throws IOException {
		JadwalJagaModel jadwalJaga = jadwalJagaService.getJadwalJagaById(idJadwalJaga);
		model.addAttribute("jadwalJaga", jadwalJaga);
		
		DokterModel dokter = dokterService.getDokterById(jadwalJaga.getIdDokter());
		model.addAttribute("dokter", dokter);
		
		List<PaviliunModel> listPaviliun = paviliunService.getAllPaviliun();
		model.addAttribute("listPaviliun", listPaviliun);
		
		List<WaktuModel> listWaktuAvailable = dokterService.getWaktuAvailable(jadwalJaga.getIdDokter());
		listWaktuAvailable.add(jadwalJaga.getWaktu());
		model.addAttribute("listWaktuAvailable", listWaktuAvailable);
		
		return "ubah-jadwal-jaga";
	}
	
	@RequestMapping(value="/success", method = RequestMethod.POST)
	private String ubahJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga, Model model) throws IOException {
		jadwalJagaService.add(jadwalJaga);
		
		return this.viewAllJadwalJaga(model);
	}
	
}
