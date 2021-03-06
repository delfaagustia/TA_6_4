package com.apap.ta46.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.repository.KamarDb;

@Service
@Transactional
public class KamarServiceImpl implements KamarService {
	@Autowired
	private KamarDb kamarDb;
	
	@Override
	public KamarModel getKamar(long id) {
		return kamarDb.findById(id);
	}

	@Override
	public List<KamarModel> getAllKamar() {
		return kamarDb.findAll();
	}
	
	@Override
	public void updateKamar(long id, KamarModel kamar) {
		KamarModel archiveKamar = this.getKamar(id);
		archiveKamar.setIdPasien(kamar.getIdPasien());
		archiveKamar.setPaviliun(kamar.getPaviliun());
		archiveKamar.setStatus(kamar.getStatus());
	}
	
	@Override
	public void addKamar(KamarModel kamar) {
		kamarDb.save(kamar);
	}
	
	public KamarModel getKamarByIdPasien(long idPasien) {
		// TODO Auto-generated method stub
		return kamarDb.findByIdPasien(idPasien);
	}

	@Override
	public List<KamarModel> getPasienInKamar() {
		// TODO Auto-generated method stub
		long idPasien = 0;
		int status = 1;
		return kamarDb.findByIdPasienNotAndStatus(idPasien, status);
	}

	@Override
	public List<KamarModel> getAllKamarByStatus(int status) {
		// TODO Auto-generated method stub
		List<KamarModel> search = new ArrayList<>();
		
		for(KamarModel kamar : kamarDb.findAll()) {
			if (kamar.getStatus() == status) {
				search.add(kamar);
			}
		}
		return search;
	}

	@Override
	public List<KamarModel> getAllKamarByIdPaviliun(long idPaviliun) {
		// TODO Auto-generated method stub
		List<KamarModel> search = new ArrayList<>();
		for(KamarModel kamar : kamarDb.findAll()) {
			if (kamar.getPaviliun().getId() == idPaviliun) {
				search.add(kamar);
			}
		}
		return search;
	}

}
