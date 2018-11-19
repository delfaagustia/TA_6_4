package com.apap.ta46.model;

import java.sql.Timestamp;
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
@Table(name="waktu")
public class WaktuModel {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Column(name="waktu", nullable=false)
	private Timestamp waktu;
	
	
	@OneToMany(mappedBy="waktu", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<JadwalJagaDokterModel> jadwalJagaDokterList;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public Timestamp getWaktu() {
		return waktu;
	}

	public void setWaktu(Timestamp waktu) {
		this.waktu = waktu;
	}

	public List<JadwalJagaDokterModel> getJadwalJagaDokterList() {
		return jadwalJagaDokterList;
	}

	public void setJadwalJagaDokterList(List<JadwalJagaDokterModel> jadwalJagaDokterList) {
		this.jadwalJagaDokterList = jadwalJagaDokterList;
	}
	
	
}
