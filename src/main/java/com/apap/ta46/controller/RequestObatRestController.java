package com.apap.ta46.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apap.ta46.model.RequestObatModel;
import com.apap.ta46.repository.RequestObatDb;
import com.apap.ta46.rest.BaseResponse;

@RestController
public class RequestObatRestController {
	@Autowired
	private RequestObatDb requestObatDb;
	
	@PostMapping(value="/addrequestobat")
	public BaseResponse<RequestObatModel> addRequestObat(@PathVariable(name = "groupId", required = true) int groupId,
            @RequestBody @Valid RequestObatModel obat, BindingResult bindingResult) {
		BaseResponse<RequestObatModel> response = new BaseResponse<RequestObatModel>();
		if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("error data");
        } else {
        	requestObatDb.save(obat);
        	response.setStatus(200);
            response.setMessage("success");
            response.setResult(obat);
        }
		return response;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	}
}
