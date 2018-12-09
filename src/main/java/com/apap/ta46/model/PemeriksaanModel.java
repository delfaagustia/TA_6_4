package com.apap.ta46.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pemeriksaan")
public class PemeriksaanModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Column(name="id_pasien", nullable=false)
	private long idPasien;
	
	@NotNull
	@Column(name="id_dokter", nullable=false)
	private long idDokter;
	
	@NotNull
	@Size(max=255)
	@Column(name="pemeriksaan", nullable=false)
	private String pemeriksaan;
	
	@NotNull
	@Column(name="waktu", nullable=false)
	private Timestamp waktu;
	
	@OneToMany(mappedBy="pemeriksaan", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<RequestObatModel> requestObatList;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getPemeriksaan() {
		return pemeriksaan;
	}

	public void setPemeriksaan(String pemeriksaan) {
		this.pemeriksaan = pemeriksaan;
	}

	public Timestamp getWaktu() {
		return waktu;
	}

	public void setWaktu(Timestamp waktu) {
		this.waktu = waktu;
	}

	public List<RequestObatModel> getRequestObatList() {
		return requestObatList;
	}

	public void setRequestObatList(List<RequestObatModel> requestObatList) {
		this.requestObatList = requestObatList;
	}

	public long getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(long idPasien) {
		this.idPasien = idPasien;
	}

	public long getIdDokter() {
		return idDokter;
	}

	public void setIdDokter(long idDokter) {
		this.idDokter = idDokter;
	}
	
}
