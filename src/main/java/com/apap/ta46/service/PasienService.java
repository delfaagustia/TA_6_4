package com.apap.ta46.service;

import java.io.IOException;
import java.util.List;

import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.RequestPasienModel;

public interface PasienService {
	PasienModel[] getAllPasien() throws IOException;
	List<PasienModel> getAllPasienRawatInap() throws IOException;
	PasienModel getPasien(String id) throws IOException;
}

