package com.apap.ta46.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.repository.PaviliunDB;

@Service
@Transactional
public class PaviliunServiceImpl implements PaviliunService{

	@Autowired
	PaviliunDB paviliunDb;
	
	@Override
	public List<PaviliunModel> getAllPaviliun() {
		// TODO Auto-generated method stub
		return paviliunDb.findAll();
	}

	@Override
	public PaviliunModel getPaviliun(Long id) {
		// TODO Auto-generated method stub
		return paviliunDb.findById(id).get();
	}

}
