package com.apap.ta46.service;

import com.apap.ta46.model.PemeriksaanModel;
import java.util.List;

public interface PenangananService {
	List<PemeriksaanModel> getPenangananByIdPasien(long idPasien);
}
