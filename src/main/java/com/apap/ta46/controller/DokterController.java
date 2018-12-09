package com.apap.ta46.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.ta46.model.WaktuModel;
import com.apap.ta46.service.DokterService;
import com.apap.ta46.service.JadwalJagaService;
import com.apap.ta46.service.WaktuService;

@Controller
public class DokterController {
	@Autowired
	WaktuService waktuService;
	
	@Autowired
	JadwalJagaService jadwalJagaService;
	
	@Autowired
	DokterService dokterService;
	
	@RequestMapping(value="/waktu-available", method=RequestMethod.GET) 
	public @ResponseBody List<WaktuModel> getAllWaktuAvailable(@RequestParam(value="idDokter") long idDokter){
		return dokterService.getWaktuAvailable(idDokter);
	}
}
