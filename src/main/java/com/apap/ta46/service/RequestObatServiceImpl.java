package com.apap.ta46.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.ta46.repository.RequestObatDb;
import com.apap.ta46.model.RequestObatModel;

@Service
@Transactional
public class RequestObatServiceImpl implements RequestObatService {
	@Autowired
	private RequestObatDb requestObatDb;
	
	@Override
	public RequestObatModel addObat(RequestObatModel obat) {
		return requestObatDb.save(obat);
	}

}
