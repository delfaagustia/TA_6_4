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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="paviliun")
public class PaviliunModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_paviliun", nullable=false)
	private String namaPaviliun;
	
	@NotNull
	@Size(max=255)
	@Column(name="tipe_pasien", nullable=false)
	private String tipePasien;
	
	@NotNull
	@Column(name="status", nullable=false)
	private int status;
	
	@OneToMany(mappedBy="paviliun", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<KamarModel> kamarList;
	
	@OneToMany(mappedBy="paviliun", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<JadwalJagaModel> jadwalJagaList;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getNamaPaviliun() {
		return namaPaviliun;
	}

	public void setNamaPaviliun(String namaPaviliun) {
		this.namaPaviliun = namaPaviliun;
	}

	public String getTipePasien() {
		return tipePasien;
	}

	public void setTipePasien(String tipePasien) {
		this.tipePasien = tipePasien;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<KamarModel> getKamarList() {
		return kamarList;
	}

	public void setKamarList(List<KamarModel> kamarList) {
		this.kamarList = kamarList;
	}

	public List<JadwalJagaModel> getJadwalJagaList() {
		return jadwalJagaList;
	}

	public void setJadwalJagaList(List<JadwalJagaModel> jadwalJagaList) {
		this.jadwalJagaList = jadwalJagaList;
	}
	
	
}
