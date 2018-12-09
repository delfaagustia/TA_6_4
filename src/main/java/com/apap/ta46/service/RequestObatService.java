package com.apap.ta46.service;

import java.util.List;

import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.model.RequestObatModel;

import com.apap.ta46.rest.BaseResponse;

public interface RequestObatService {
	void addObat(RequestObatModel obat);
	RequestObatModel findObatById(long id);
	BaseResponse postRequestObat(List<RequestObatModel> requestObat, long idPemeriksaan);
	List<RequestObatModel> getAllObat();
}
