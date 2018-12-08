package com.apap.ta46.service;

import java.io.IOException;
import java.util.Set;

import com.apap.ta46.model.DokterModel;

public interface DokterService {
	DokterModel getDokterById(long id) throws IOException ;
	Set<DokterModel> getAllDokter() throws IOException ;
	DokterModel[] getAllDokterSIAppointment() throws IOException;
}
