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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="request_obat")
public class RequestObatModel implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_obat", nullable=false)
	private String namaObat;
	
	@NotNull
	@Column(name="jumlah", nullable=false)
	private int jumlah;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pemeriksaan", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PemeriksaanModel pemeriksaan;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getNamaObat() {
		return namaObat;
	}

	public void setNamaObat(String namaObat) {
		this.namaObat = namaObat;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}
	
	
}
