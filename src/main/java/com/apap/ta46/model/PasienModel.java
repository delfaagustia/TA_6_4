package com.apap.ta46.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PasienModel {
	
	private long Id;
	private String nama;
	//private StatusPasienModel status;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	//public StatusPasienModel getStatus() {
		//return status;
	//}
	//public void setStatus(StatusPasienModel status) {
		//this.status = status;
	//}
	
	
	
	
}
