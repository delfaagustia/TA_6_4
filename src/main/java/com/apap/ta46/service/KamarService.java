package com.apap.ta46.service;

import java.util.List;

import com.apap.ta46.model.KamarModel;

public interface KamarService {
	KamarModel getKamar(long id);
	List<KamarModel> getAllKamar();
	List<KamarModel> getPasienInKamar();
	void addKamar(KamarModel kamar);
	void updateKamar(long id, KamarModel kamar);
	KamarModel getKamarByIdPasien(long idPasien);
	List<KamarModel> getAllKamarByStatus(int status);
	List<KamarModel> getAllKamarByIdPaviliun(long idPaviliun);
}

