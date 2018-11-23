package com.apap.ta46.service;

import java.util.List;

import com.apap.ta46.model.KamarModel;

public interface KamarService {
	List<KamarModel> getAllKamar();
	void addKamar(KamarModel kamar);
	void updateKamar(KamarModel kamar);
	KamarModel getKamar(long id);
}
