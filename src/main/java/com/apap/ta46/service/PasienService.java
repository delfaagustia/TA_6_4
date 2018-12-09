package com.apap.ta46.service;

import java.io.IOException;

import com.apap.ta46.model.PasienModel;
import com.apap.ta46.rest.BaseResponse;

public interface PasienService {
	PasienModel[] getAllPasien() throws IOException;
	PasienModel getPasien(String id) throws IOException;
	public BaseResponse postPasien(PasienModel pasien);
}

