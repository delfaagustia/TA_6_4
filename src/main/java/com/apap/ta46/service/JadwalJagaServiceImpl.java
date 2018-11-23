package com.apap.ta46.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.ta46.model.JadwalJagaModel;
import com.apap.ta46.repository.JadwalJagaDb;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService{
	
	@Autowired
	JadwalJagaDb jadwalJagaDb;
	
	@Override
	public List<JadwalJagaModel> getAllJadwalJaga() {
		// TODO Auto-generated method stub
		return jadwalJagaDb.findAll();
	}

}
