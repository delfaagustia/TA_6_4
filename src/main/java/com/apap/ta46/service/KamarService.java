package com.apap.ta46.service;

import java.util.List;

import com.apap.ta46.model.KamarModel;

public interface KamarService {
	KamarModel getKamarDetailById(long id);
	KamarModel findKamarDetailByStatus(int status);
	List<KamarModel> getAllKamar();
	List<KamarModel> findKamarByPaviliun(long idPaviliun);
}
