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
@Table(name="jadwal_jaga")
public class JadwalJagaModel implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(max=255)
	@Column(name="status_dokter", nullable=false)
	private String statusDokter;
	
	@NotNull
	@Size(max=255)
	@Column(name="daftar_hari_jaga", nullable=false)
	private String daftarHariJaga;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_dokter", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private DokterModel dokter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_paviliun", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PaviliunModel paviliun;
	
	@OneToMany(mappedBy="jadwal_jaga", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<HariJagaDokterModel> hariJagaDokterList;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getStatusDokter() {
		return statusDokter;
	}

	public void setStatusDokter(String statusDokter) {
		this.statusDokter = statusDokter;
	}

	public String getDaftarHariJaga() {
		return daftarHariJaga;
	}

	public void setDaftarHariJaga(String daftarHariJaga) {
		this.daftarHariJaga = daftarHariJaga;
	}

	public DokterModel getDokter() {
		return dokter;
	}

	public void setDokter(DokterModel dokter) {
		this.dokter = dokter;
	}

	public List<HariJagaDokterModel> getHariJagaDokterList() {
		return hariJagaDokterList;
	}

	public void setHariJagaDokterList(List<HariJagaDokterModel> hariJagaDokterList) {
		this.hariJagaDokterList = hariJagaDokterList;
	}

	public PaviliunModel getPaviliun() {
		return paviliun;
	}

	public void setPaviliun(PaviliunModel paviliun) {
		this.paviliun = paviliun;
	}
	
	
	
}
