package com.apap.ta46.controller;


import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.RequestPasienModel;
import com.apap.ta46.repository.RequestPasienDb;
import com.apap.ta46.rest.BaseResponse;
import com.apap.ta46.service.PasienService;


@RestController
@RequestMapping("/api")
public class ApiIgdController {
	@Autowired
	RequestPasienDb requestPasienDb;
	
	@Autowired
	    RestTemplate restTemplate;
	   
	@Autowired
		private PasienService pasienService;
	
	@PostMapping(value = "/daftar-ranap")
    public BaseResponse<RequestPasienModel> addPasienRujukan (@RequestBody @Valid PasienModel pasien, BindingResult bindingResult) throws IOException {
        BaseResponse<RequestPasienModel> response = new BaseResponse<RequestPasienModel>();
        
        RequestPasienModel pasienRujukan = new RequestPasienModel ();
        if (bindingResult.hasErrors() || pasien.equals(null) || pasien.getId() <= 0) { //INI TANYA LAGI
            response.setStatus(500);
            response.setMessage("error data");
        } else {
        	pasienRujukan.setIdPasien(pasien.getId());
        	pasienRujukan.setAssign(0);//belum assign kamar
        	requestPasienDb.save(pasienRujukan);
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(pasienRujukan);
           
        
            PasienModel pasienFull = pasienService.getPasien(Long.toString(pasienRujukan.getIdPasien()));
        	
            //get dulu, bukak objeknya, ganti ,post lagi
            //post status ke si appointment
           //// String path = "http://si-appointment.herokuapp.com/api/4/updatePasien";
          // BaseResponse<PasienModel> detail = restTemplate.postForObject(path, pasienRujukan, BaseResponse.class);
        }
        return response;
    }
}
