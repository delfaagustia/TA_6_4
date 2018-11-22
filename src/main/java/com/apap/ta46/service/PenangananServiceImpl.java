package com.apap.ta46.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.repository.PenangananDB;

@Service
@Transactional
public class PenangananServiceImpl implements PenangananService{
	@Autowired
	PenangananDB penangananDb;
	
	@Override
	public List<PemeriksaanModel> getPenangananByIdPasien(long idPasien) {
		// TODO Auto-generated method stub
		return penangananDb.findAllByIdPasien(idPasien);
	}

	@Override
	public void add(PemeriksaanModel penanganan) {
		// TODO Auto-generated method stub
		penangananDb.save(penanganan);
	}

	@Override
	public PemeriksaanModel getPenangananById(long idPenanganan) {
		// TODO Auto-generated method stub
		return penangananDb.findById(idPenanganan).get();
	}

}
