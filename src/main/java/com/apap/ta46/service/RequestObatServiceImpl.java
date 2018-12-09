package com.apap.ta46.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.repository.RequestObatDb;
import com.apap.ta46.rest.BaseResponse;
import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.model.RequestObatModel;

@Service
@Transactional
public class RequestObatServiceImpl implements RequestObatService {
	@Autowired
	private RequestObatDb requestObatDb;
	
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	@Override
	public void addObat(RequestObatModel obat) {
		requestObatDb.save(obat);
	}

	@Override
	public RequestObatModel findObatById(long id) {
		return requestObatDb.findById(id);
	}

	@Override
	public BaseResponse postRequestObat(List<RequestObatModel> requestObat, long idPemeriksaan) {
		PemeriksaanModel archive = pemeriksaanService.getPemeriksaan(idPemeriksaan);
		BaseResponse response = null;
		for(RequestObatModel pem: requestObat) {
			 pem.setPemeriksaan(archive);
			 this.addObat(pem);
			 HttpEntity<RequestObatModel> entity = new HttpEntity<RequestObatModel>(pem);
			 RestTemplate restTemplate = new RestTemplate();
			 ResponseEntity<BaseResponse> obatEntity = restTemplate.exchange("https://335d9e5c-f224-4922-ad16-1388bfe9068d.mock.pstmn.io/obat", HttpMethod.POST, entity, BaseResponse.class);
			 response = obatEntity.getBody();
		}
		return response;
	}

	@Override
	public List<RequestObatModel> getAllObat() {
		// TODO Auto-generated method stub
		return requestObatDb.findAll();
	}
}
