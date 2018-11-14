package com.apap.ta46.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="hari_jaga_dokter")
public class HariJagaDokterModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_dokter", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private DokterModel dokter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_jadwal_jaga", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private JadwalJagaModel jadwalJaga;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DokterModel getDokter() {
		return dokter;
	}

	public void setDokter(DokterModel dokter) {
		this.dokter = dokter;
	}

	public JadwalJagaModel getJadwalJaga() {
		return jadwalJaga;
	}

	public void setJadwalJaga(JadwalJagaModel jadwalJaga) {
		this.jadwalJaga = jadwalJaga;
	}
	
}
