package com.apap.ta46.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.repository.PaviliunDb;

@Service
@Transactional
public class PaviliunServiceImpl implements PaviliunService{
	@Autowired
	private PaviliunDb paviliunDb;
	
	@Autowired
	private KamarService kamarService;

	@Override
	public List<PaviliunModel> getAllPaviliun() {
		return paviliunDb.findAll();
	}

	@Override
	public PaviliunModel getPaviliun(Long id) {
		// TODO Auto-generated method stub
		return paviliunDb.findById(id).get();
	}

	@Override
	public List<KamarModel> getKamarAvailable(Long idPaviliun) {
		// TODO Auto-generated method stub
		List<KamarModel> kamarKamar = kamarService.findKamarByPaviliun(idPaviliun);
		List<KamarModel> kamarAvailable = new ArrayList<KamarModel>();
		for(KamarModel kamar : kamarKamar) {
			if(kamar.getStatus()==0) {
				kamarAvailable.add(kamar);
			}
		}
		return kamarAvailable;
	}

	
}
