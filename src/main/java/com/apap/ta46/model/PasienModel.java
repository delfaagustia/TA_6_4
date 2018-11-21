package com.apap.ta46.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PasienModel {
	
	private long Id;
	private String nama;
	private Date tanggalRujukan;
	private StatusPasienModel statusPasien;
	private String poliRujukan;
	
	public Date getTanggalRujukan() {
		return tanggalRujukan;
	}
	public void setTanggalRujukan(Date tanggalRujukan) {
		this.tanggalRujukan = tanggalRujukan;
	}
	public StatusPasienModel getStatusPasien() {
		return statusPasien;
	}
	public void setStatusPasien(StatusPasienModel statusPasien) {
		this.statusPasien = statusPasien;
	}
	public String getPoliRujukan() {
		return poliRujukan;
	}
	public void setPoliRujukan(String poliRujukan) {
		this.poliRujukan = poliRujukan;
	}
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
	
	
	
	
}
