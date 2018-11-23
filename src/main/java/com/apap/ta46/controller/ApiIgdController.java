package com.apap.ta46.controller;


import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.ta46.model.PasienModel;
import com.apap.ta46.model.RequestPasienModel;
import com.apap.ta46.model.StatusPasienModel;
import com.apap.ta46.repository.RequestPasienDb;
import com.apap.ta46.rest.BaseResponse;
import com.apap.ta46.service.PasienService;


@RestController
@RequestMapping("/api")
public class ApiIgdController {
	@Autowired
	RequestPasienDb requestPasienDb;
	
	@Autowired
	    RestTemplate restTemplate;
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	@Autowired
		private PasienService pasienService;
	
	@PostMapping(value = "/daftar-ranap")
    public BaseResponse<RequestPasienModel> addPasienRujukan (@RequestBody @Valid PasienModel pasien, BindingResult bindingResult) throws IOException {
        BaseResponse<RequestPasienModel> response = new BaseResponse<RequestPasienModel>();
        
        RequestPasienModel pasienRujukan = new RequestPasienModel ();
        if (bindingResult.hasErrors() || pasien.equals(null) || pasien.getId() <= 0) { //INI TANYA LAGI
            response.setStatus(500);
            response.setMessage("error data");
        } else {
        	pasienRujukan.setIdPasien(pasien.getId());
        	pasienRujukan.setAssign(0);//belum assign kamar
        	requestPasienDb.save(pasienRujukan);
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(pasienRujukan);
           
            //get objek pasien dari si appt
            System.out.println("masuksini del");
            PasienModel pasienFull = pasienService.getPasien(Long.toString(pasienRujukan.getIdPasien()));
        	
          //get dulu, bukak objeknya, ganti ,post lagi
            //buat objek status
            StatusPasienModel statusMasukRanap = new StatusPasienModel();
            statusMasukRanap.setId(4);
            statusMasukRanap.setJenis("Mendaftar di Rawat Inap");
            
            //set baru status pasien biar di ranap
            pasienFull.setStatusPasien(statusMasukRanap);
            
            System.out.println(pasienFull.getStatusPasien().getId() + pasienFull.getStatusPasien().getJenis() +"sebelum");
            
            //post status ke si appointment (ini yang buat post ulangnya)
            String path = "http://si-appointment.herokuapp.com/api/4/updatePasien";
            System.out.println(path);
            BaseResponse<PasienModel> detail = restTemplate.postForObject(path, pasienFull, BaseResponse.class);
            System.out.println(detail.getStatus() + "ahhaha");
            System.out.println(detail.getMessage() + "ini pesan");
            PasienModel pasienEdited = pasienService.getPasien(Long.toString(pasienRujukan.getIdPasien()));
            System.out.println(pasienEdited.getStatusPasien().getId() + pasienEdited.getStatusPasien().getJenis() + "ini harusnya");
            
            
        }
        return response;
    }
}
