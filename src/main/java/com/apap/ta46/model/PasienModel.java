package com.apap.ta46.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="pasien")
public class PasienModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable=false)
	private String nama;
	
	@NotNull
	@Column(name="status", nullable=false)
	private int status;
	
	@OneToMany(mappedBy="pasien", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PemeriksaanModel> pemeriksaanList;
	
	@OneToMany(mappedBy="pasien", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<RequestObatModel> requestObatList;
	
	@OneToOne(mappedBy="pasien", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private RequestPasienModel requestPasien;
	
	@OneToOne(mappedBy="pasien", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private KamarModel kamar;
	
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<PemeriksaanModel> getPemeriksaanList() {
		return pemeriksaanList;
	}

	public void setPemeriksaanList(List<PemeriksaanModel> pemeriksaanList) {
		this.pemeriksaanList = pemeriksaanList;
	}

	public List<RequestObatModel> getRequestObatList() {
		return requestObatList;
	}

	public void setRequestObatList(List<RequestObatModel> requestObatList) {
		this.requestObatList = requestObatList;
	}

	public RequestPasienModel getRequestPasien() {
		return requestPasien;
	}

	public void setRequestPasien(RequestPasienModel requestPasien) {
		this.requestPasien = requestPasien;
	}

	public KamarModel getKamar() {
		return kamar;
	}

	public void setKamar(KamarModel kamar) {
		this.kamar = kamar;
	}
	
	
}
