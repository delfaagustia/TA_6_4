package com.apap.ta46.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.repository.KamarDb;
import com.apap.ta46.rest.BaseResponse;
import com.apap.ta46.service.KamarService;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	KamarDb kamarDb;
	
	@Autowired
	KamarService kamarService;
		
	@GetMapping(value = "/get-all-kamar")
	public BaseResponse<List<KamarModel>> getPasienInKamar () {
        BaseResponse<List<KamarModel>> response = new BaseResponse<List<KamarModel>>();
        
        if (response.equals(null)) {
        	response.setStatus(500);
        	response.setMessage("error data");
        	response.setResult(null);
        }
        else {
        	response.setStatus(200);
        	response.setMessage("success");
        	response.setResult(kamarService.getPasienInKamar());
        }
        
        List<KamarModel> kamars = kamarService.getPasienInKamar();
        for (KamarModel kamar : kamars) {
        	System.out.println(kamar.getIdPasien());
        }

        return response;
    }
	
}
