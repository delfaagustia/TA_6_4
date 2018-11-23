package com.apap.ta46.service;

import java.io.IOException;
import java.util.List;

import com.apap.ta46.model.PasienModel;

public interface PasienService {
	PasienModel[] getAllPasien() throws IOException;
	PasienModel getPasien(String id) throws IOException;
}
