package com.apap.ta46.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.ta46.model.KamarModel;
import com.apap.ta46.model.WaktuModel;
import com.apap.ta46.repository.KamarDb;
import com.apap.ta46.repository.WaktuDb;

@Service
@Transactional
public class WaktuServiceImpl implements WaktuService {
	@Autowired
	private WaktuDb waktuDb;
	
	@Override
	public List<WaktuModel> getAllWaktu() {
		// TODO Auto-generated method stub
		return waktuDb.findAll();
	}

}