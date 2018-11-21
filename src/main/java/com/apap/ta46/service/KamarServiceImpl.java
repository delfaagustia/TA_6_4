package com.apap.ta46.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.repository.KamarDb;

@Service
@Transactional
public class KamarServiceImpl implements KamarService {
	@Autowired
	private KamarDb kamarDb;
	
	@Override
	public List<KamarModel> getAllKamar() {
		return kamarDb.findAll();
	}

	@Override
	public void addKamar(KamarModel kamar) {
		String idPaviliun = String.valueOf(kamar.getPaviliun().getId());
		int banyakKamar = kamar.getPaviliun().getKamarList().size();
		String urutan = "";
		if(banyakKamar < 10) {
			urutan = "0" + String.valueOf(banyakKamar);
		}else {
			urutan = String.valueOf(banyakKamar);
		}
		String id = idPaviliun + urutan;
		kamar.setId(Long.parseLong(id));
		kamarDb.save(kamar);		
	}

	@Override
	public KamarModel getKamar(long id) {
		// TODO Auto-generated method stub
		return kamarDb.findById(id);
	}

	@Override
	public void updateKamar(KamarModel kamar) {
		System.out.println(kamar.getId());
		KamarModel archiveKamar = this.getKamar(kamar.getId());
		archiveKamar.setIdPasien(kamar.getIdPasien());
		archiveKamar.setPaviliun(kamar.getPaviliun());
		archiveKamar.setStatus(kamar.getStatus());
	}

}
