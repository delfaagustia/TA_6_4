package com.apap.ta46.service;

import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.model.RequestObatModel;

import com.apap.ta46.rest.BaseResponse;

public interface RequestObatService {
	BaseResponse<RequestObatModel> addObat(RequestObatModel obat);
	RequestObatModel findObatById(long id);
}
