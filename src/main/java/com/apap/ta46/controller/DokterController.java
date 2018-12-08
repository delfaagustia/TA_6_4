package com.apap.ta46.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.ta46.model.JadwalJagaModel;
import com.apap.ta46.model.WaktuModel;
import com.apap.ta46.service.JadwalJagaService;
import com.apap.ta46.service.WaktuService;

@Controller
public class DokterController {
	@Autowired
	WaktuService waktuService;
	
	@Autowired
	JadwalJagaService jadwalJagaService;
	
	@RequestMapping(value="/waktu-available", method=RequestMethod.GET) 
	public @ResponseBody List<WaktuModel> getAllWaktuAvailable(@RequestParam(value="idDokter") long idDokter){
		List<WaktuModel> listWaktuAvailable = new ArrayList<WaktuModel>();
		List<WaktuModel> listWaktu = waktuService.getAllWaktu();
		System.out.println(listWaktu);
		
		List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getAllJadwalJagaByIdDokter(idDokter);
		List<WaktuModel> listWaktuTaken = new ArrayList<WaktuModel>();
		for (JadwalJagaModel jadwal: listJadwalJaga) {
			listWaktuTaken.add(jadwal.getWaktu());
		}
		
		for (WaktuModel waktu: listWaktu) {
			if (!listWaktuTaken.contains(waktu)) {
				listWaktuAvailable.add(waktu);
			}
		}
		
		System.out.println(listWaktuAvailable);
		
		return listWaktuAvailable;
	}
}
