package com.apap.ta46.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.DokterModel;
import com.apap.ta46.model.JadwalJagaModel;
import com.apap.ta46.rest.Setting;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {
	@Autowired
	JadwalJagaService jadwalJagaService;
	
	@Autowired
    RestTemplate restTemplate;
	
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

}
