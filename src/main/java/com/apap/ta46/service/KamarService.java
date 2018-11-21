package com.apap.ta46.service;

import com.apap.ta46.model.KamarModel;
import java.util.List;

public interface KamarService {
	List<KamarModel> getAllKamar();
	KamarModel getKamarByIdPasien(long idPasien);
}
