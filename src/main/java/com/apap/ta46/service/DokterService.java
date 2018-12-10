package com.apap.ta46.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.apap.ta46.model.DokterModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.WaktuModel;

public interface DokterService {
	DokterModel getDokterById(long id) throws IOException ;
	Set<DokterModel> getAllDokter() throws IOException ;
	DokterModel[] getAllDokterSIAppointment() throws IOException;
	List<WaktuModel> getWaktuAvailable(long idDokter); 
	List<WaktuModel> getAllWaktuJagaByPaviliunAndIdDokter(long idDokter, long idPaviliun);
}
