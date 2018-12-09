package com.apap.ta46.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.ta46.model.RequestObatModel;
import com.apap.ta46.repository.RequestObatDb;
import com.apap.ta46.rest.BaseResponse;

@RestController
public class RequestObatRestController {
	@Autowired
	private RequestObatDb requestObatDb;
	
	@PostMapping(value="/api/obat/saves/")
	public BaseResponse<RequestObatModel> addRequestObat(
            @RequestBody @Valid RequestObatModel obat, BindingResult bindingResult) {
		BaseResponse<RequestObatModel> response = new BaseResponse<RequestObatModel>();
		System.out.println("masuk");
		if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("error data");
        } else {
        	//requestObatDb.save(obat);
        	response.setStatus(200);
            response.setMessage("success");
            response.setResult(obat);
        }
		return response;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	}
}
