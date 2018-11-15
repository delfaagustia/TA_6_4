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

@Entity
@Table(name="dokter")
public class DokterModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable=false)
	private String nama;
	
	@OneToMany(mappedBy="dokter", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<JadwalJagaModel> jadwalJagaList;
	
	@OneToMany(mappedBy="dokter", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PemeriksaanModel> pemeriksaanList;

	public long getId() {
		return Id;
	}

	public String getNama() {
		return nama;
	}

	public List<JadwalJagaModel> getJadwalJagaList() {
		return jadwalJagaList;
	}

	public List<PemeriksaanModel> getPemeriksaanList() {
		return pemeriksaanList;
	}
	
	
}
