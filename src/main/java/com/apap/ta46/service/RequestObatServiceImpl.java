package com.apap.ta46.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.repository.RequestObatDb;
import com.apap.ta46.rest.BaseResponse;
import com.apap.ta46.model.RequestObatModel;

@Service
@Transactional
public class RequestObatServiceImpl implements RequestObatService {
	@Autowired
	private RequestObatDb requestObatDb;
	
	@Override
	public BaseResponse<RequestObatModel> addObat(RequestObatModel obat) {
		RestTemplate restTemplate = new RestTemplate();
		BaseResponse<RequestObatModel> reqObat = restTemplate.postForObject("http://localhost:2000/obat/save", obat, BaseResponse.class);
		return reqObat;
	}
}
