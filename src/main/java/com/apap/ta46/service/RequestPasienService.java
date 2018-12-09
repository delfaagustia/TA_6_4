package com.apap.ta46.service;

import java.util.List;

import com.apap.ta46.model.RequestPasienModel;

public interface RequestPasienService {
	List<RequestPasienModel> getAllRequestPasien();
	RequestPasienModel getRequestPasienByIdPasien(long idPasien);
	RequestPasienModel getRequestPasienByIdPasienPulang(long idPasien);
	void updateRequestPasien(RequestPasienModel req);
}
