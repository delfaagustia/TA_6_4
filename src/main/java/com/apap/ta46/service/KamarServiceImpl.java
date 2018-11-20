package com.apap.ta46.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.repository.KamarDB;

@Service
@Transactional
public class KamarServiceImpl implements KamarService{
	@Autowired
	private KamarDB kamarDb;
	
	@Override
	public List<KamarModel> getAllKamar() {
		// TODO Auto-generated method stub
		return kamarDb.findAll();
	}

}
