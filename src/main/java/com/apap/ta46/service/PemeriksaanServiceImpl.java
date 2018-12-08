package com.apap.ta46.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.repository.PemeriksaanDb;

@Service
@Transactional
public class PemeriksaanServiceImpl implements PemeriksaanService {
	@Autowired
	private PemeriksaanDb pemeriksaanDb;
	
	@Override
	public List<PemeriksaanModel> getAllPemeriksaan() {
		return pemeriksaanDb.findAll();
	}

	@Override
	public PemeriksaanModel getPemeriksaan(long id) {
		return pemeriksaanDb.findById(id);
	}

}
