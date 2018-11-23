package com.apap.ta46.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.repository.KamarDb;
import com.apap.ta46.rest.BaseResponse;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	KamarDb kamarDb;
	
	@GetMapping(value = "/get-all-kamar")
	public BaseResponse<List<KamarModel>> getAllKamar () {
        BaseResponse<List<KamarModel>> response = new BaseResponse<List<KamarModel>>();
        
        if (response.equals(null)) {
        	response.setStatus(500);
        	response.setMessage("error data");
        	response.setResult(null);
        }
        else {
        	response.setStatus(200);
        	response.setMessage("success");
        	response.setResult(kamarDb.findAll());
        }
        System.out.println(response);

        return response;
    }
	
}
