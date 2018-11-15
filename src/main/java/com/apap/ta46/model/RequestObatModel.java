package com.apap.ta46.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="request_obat")
public class RequestObatModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_obat", nullable=false)
	private String nama_obat;
	
	@NotNull
	@Column(name="jumlah", nullable=false)
	private int jumlah;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pemeriksaan", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PemeriksaanModel pemeriksaan;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pasien", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PasienModel pasien;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getNama_obat() {
		return nama_obat;
	}

	public void setNama_obat(String nama_obat) {
		this.nama_obat = nama_obat;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public PemeriksaanModel getPemeriksaan() {
		return pemeriksaan;
	}

	public void setPemeriksaan(PemeriksaanModel pemeriksaan) {
		this.pemeriksaan = pemeriksaan;
	}

	public PasienModel getPasien() {
		return pasien;
	}

	public void setPasien(PasienModel pasien) {
		this.pasien = pasien;
	}
	
	
}
