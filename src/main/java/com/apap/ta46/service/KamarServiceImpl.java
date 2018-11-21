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
	public KamarModel getKamarDetailById(long id) {
		return kamarDb.findById(id);
	}

	@Override
	public List<KamarModel> getAllKamar() {
		return kamarDb.findAll();
	}

	@Override
	public KamarModel findKamarDetailByStatus(int status) {
		return kamarDb.findByStatus(status);
	}
	
	@Override
	public List<KamarModel> findKamarByPaviliun(long idPaviliun) {
		List<KamarModel> search = new ArrayList<>();
		
		for(KamarModel kamar : kamarDb.findAll()) {
			if (kamar.getPaviliun().getId() == idPaviliun && kamar.getStatus() == 0) {
				search.add(kamar);
			}
		}
		return search;
	}
	
	

}
