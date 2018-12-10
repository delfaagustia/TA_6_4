package com.apap.ta46.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.DokterModel;
import com.apap.ta46.model.JadwalJagaModel;
import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.PaviliunModel;
import com.apap.ta46.model.WaktuModel;
import com.apap.ta46.rest.Setting;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.apap.ta46.service.WaktuService;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {
	@Autowired
	JadwalJagaService jadwalJagaService;
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	WaktuService waktuService;
	
	@Autowired
	PaviliunService paviliunService;
	
    @Bean
    public RestTemplate rest() {
    	return new RestTemplate();
    }
	
	@Override
	public DokterModel getDokterById(long idDokter) throws IOException {
		// TODO Auto-generated method stub
		String path = Setting.dokterUrl + idDokter;
		String stringDokter = restTemplate.getForObject(path, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(stringDokter);
		JsonNode result = node.get("result");
		DokterModel dokter = mapper.treeToValue(result, DokterModel.class);
		return dokter;
	}

	@Override
	public Set<DokterModel> getAllDokter() throws IOException {
		// TODO Auto-generated method stub
		List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getAllJadwalJaga();
		Set<DokterModel> listDokter = new HashSet<DokterModel>();
		
		for (JadwalJagaModel jadwal: listJadwalJaga) {
			listDokter.add(getDokterById(jadwal.getIdDokter()));
		}
		
		return listDokter;
	}
	
	@Override
	public DokterModel[] getAllDokterSIAppointment() throws IOException {
		String path = "http://si-appointment.herokuapp.com/api/4/getAllDokter";
		String alldokter= restTemplate.getForObject(path, String.class);
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node = mapper.readTree(alldokter);
    	JsonNode result = node.get("result");
    	DokterModel[] listdokter = mapper.treeToValue(result, DokterModel[].class);
		return listdokter;
	}

	@Override
	public List<WaktuModel> getWaktuAvailable(long idDokter) {
		// TODO Auto-generated method stub
		
		List<WaktuModel> listWaktuAvailable = new ArrayList<WaktuModel>();
		List<WaktuModel> listWaktu = waktuService.getAllWaktu();
		
		List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getAllJadwalJagaByIdDokter(idDokter);
		List<WaktuModel> listWaktuTaken = new ArrayList<WaktuModel>();
		for (JadwalJagaModel jadwal: listJadwalJaga) {
			listWaktuTaken.add(jadwal.getWaktu());
		}
		
		for (WaktuModel waktu: listWaktu) {
			if (!listWaktuTaken.contains(waktu)) {
				listWaktuAvailable.add(waktu);
			}
		}
		
		return listWaktuAvailable;
	}

	@Override
	public List<WaktuModel> getAllWaktuJagaByPaviliunAndIdDokter(long idDokter, long idPaviliun) {
		// TODO Auto-generated method stub
		PaviliunModel paviliun = paviliunService.getPaviliunById(idPaviliun);
		System.out.println(paviliun.getNamaPaviliun());
		List<WaktuModel> listWaktuJagaBasedOnPaviliunAndIdDokter = new ArrayList<>();
		
		List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getAllJadwalJagaByIdDokter(idDokter);
		System.out.println(listJadwalJaga.size());
		for (JadwalJagaModel jadwal: listJadwalJaga) {
			if (jadwal.getPaviliun().equals(paviliun)) {
				listWaktuJagaBasedOnPaviliunAndIdDokter.add(jadwal.getWaktu());
			}
		}
		System.out.println(listWaktuJagaBasedOnPaviliunAndIdDokter.size());
		return listWaktuJagaBasedOnPaviliunAndIdDokter;
	}

}
