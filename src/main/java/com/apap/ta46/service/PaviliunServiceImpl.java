package com.apap.ta46.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.repository.PaviliunDb;

@Service
@Transactional
public class PaviliunServiceImpl implements PaviliunService{
	@Autowired
	private PaviliunDb paviliunDb;

	@Override
	public List<PaviliunModel> getAllPaviliun() {
		return paviliunDb.findAll();
	}

}
