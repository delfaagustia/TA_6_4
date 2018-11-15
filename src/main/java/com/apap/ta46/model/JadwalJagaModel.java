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
@Table(name="jadwalJaga")
public class JadwalJagaModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
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
		
	@NotNull
	@Size(max=255)
	@Column(name="status_dokter", nullable=false)
	private String statusDokter;
	
	@OneToMany(mappedBy="jadwalJaga", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<JadwalJagaDokterModel> jadwalJagaDokterList;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public DokterModel getDokter() {
		return dokter;
	}

	public void setDokter(DokterModel dokter) {
		this.dokter = dokter;
	}

	public PaviliunModel getPaviliun() {
		return paviliun;
	}

	public void setPaviliun(PaviliunModel paviliun) {
		this.paviliun = paviliun;
	}

	public String getStatusDokter() {
		return statusDokter;
	}

	public void setStatusDokter(String statusDokter) {
		this.statusDokter = statusDokter;
	}

	public List<JadwalJagaDokterModel> getJadwalJagaDokterList() {
		return jadwalJagaDokterList;
	}

	public void setJadwalJagaDokterList(List<JadwalJagaDokterModel> jadwalJagaDokterList) {
		this.jadwalJagaDokterList = jadwalJagaDokterList;
	}
	
	
	
}
