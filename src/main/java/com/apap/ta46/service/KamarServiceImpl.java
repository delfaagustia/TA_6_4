package com.apap.ta46.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
		// TODO Auto-generated method stub
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
	
	public KamarModel getKamarByIdPasien(long idPasien) {
		// TODO Auto-generated method stub
		return kamarDb.findByIdPasien(idPasien);
	}

}
