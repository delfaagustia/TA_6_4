package com.apap.ta46.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.repository.KamarDb;

@Service
@Transactional
public class KamarServiceImpl implements KamarService{
	@Autowired
	private KamarDb kamarDb;
	
	@Override
	public List<KamarModel> getAllKamar() {
		// TODO Auto-generated method stub
		return kamarDb.findAll();
	}

	@Override
	public KamarModel getKamarByIdPasien(long idPasien) {
		// TODO Auto-generated method stub
		return kamarDb.findByIdPasien(idPasien);
	}

	@Override
	public KamarModel addKamar(KamarModel kamar) {
		// TODO Auto-generated method stub
		return kamarDb.save(kamar);
	}

	@Override
	public KamarModel getKamarById(long id) {
		// TODO Auto-generated method stub
		return kamarDb.findById(id);
	}

}