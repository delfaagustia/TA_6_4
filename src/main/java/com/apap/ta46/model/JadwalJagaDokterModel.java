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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="jadwal_jaga_dokter")
public class JadwalJagaDokterModel implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Column(name="nama")
	private int nama;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_hari", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private HariModel hari;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_jadwal_jaga", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private JadwalJagaModel jadwalJaga;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public int getNama() {
		return nama;
	}

	public void setNama(int nama) {
		this.nama = nama;
	}

	public HariModel getHari() {
		return hari;
	}

	public void setHari(HariModel hari) {
		this.hari = hari;
	}

	public JadwalJagaModel getJadwalJaga() {
		return jadwalJaga;
	}

	public void setJadwalJaga(JadwalJagaModel jadwalJaga) {
		this.jadwalJaga = jadwalJaga;
	}
	
	
		
}
