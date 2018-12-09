package com.apap.ta46.service;

import java.util.List;

import com.apap.ta46.model.PemeriksaanModel;

public interface PemeriksaanService {
	List<PemeriksaanModel> getAllPemeriksaan();
	PemeriksaanModel getPemeriksaan(long id);

}
