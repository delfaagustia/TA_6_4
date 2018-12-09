package com.apap.ta46.service;

import com.apap.ta46.model.JadwalJagaModel;
import java.util.List;

public interface JadwalJagaService {
	List<JadwalJagaModel> getAllJadwalJaga();
	void add(JadwalJagaModel jadwalJaga);
	List<JadwalJagaModel> getAllJadwalJagaByIdDokter(long idDokter);
	JadwalJagaModel getJadwalJagaById(long idJadwalJaga);
	void removeById(long idJadwalJaga);
}
