package com.apap.ta46.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.repository.PaviliunDb;

@Service
@Transactional
public class PaviliunServiceImpl implements PaviliunService {
	@Autowired
	private PaviliunDb paviliunDb;
	
	@Override
	public List<PaviliunModel> getAllPaviliun() {
		// TODO Auto-generated method stub
		return paviliunDb.findAll();
	}

}
