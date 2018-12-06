package com.apap.ta46.service;

import java.util.List;

import com.apap.ta46.model.KamarModel;

public interface KamarService {
	KamarModel getKamar(long id);
	KamarModel findKamarDetailByStatus(int status);
	List<KamarModel> getAllKamar();
	List<KamarModel> findKamarByPaviliun(long idPaviliun);
	List<KamarModel> findKamarByStatus(int status);
	List<KamarModel> findKamarByPaviliunAndStatus(long idPaviliun, int status);
	void addKamar(KamarModel kamar);
	void updateKamar(KamarModel kamar);
	KamarModel getKamarByIdPasien(long idPasien);
}

