package com.apap.ta46.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.ta46.model.RequestPasienModel;
import com.apap.ta46.repository.RequestPasienDb;

@Service
public class RequestPasienServiceImpl implements RequestPasienService{

	@Autowired
	RequestPasienDb requestPasienDb;
	
	@Override
	public List<RequestPasienModel> getAllRequestPasien() {
		return requestPasienDb.findAll();
	}

	@Override
	public RequestPasienModel getRequestPasienByIdPasien(long idPasien) {
		// TODO Auto-generated method stub
		return requestPasienDb.findByIdPasien(idPasien);
	}

}
