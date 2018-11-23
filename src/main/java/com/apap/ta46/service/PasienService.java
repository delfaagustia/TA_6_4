package com.apap.ta46.service;

import java.io.IOException;

import com.apap.ta46.model.PasienModel;

public interface PasienService {

	void getPasien(String id) throws IOException;
	PasienModel[] getAllPasien() throws IOException;

}
