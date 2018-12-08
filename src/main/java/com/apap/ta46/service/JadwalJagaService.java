package com.apap.ta46.service;

import com.apap.ta46.model.JadwalJagaModel;
import java.util.List;

public interface JadwalJagaService {
	List<JadwalJagaModel> getAllJadwalJaga();
	void add(JadwalJagaModel jadwalJaga);
}
