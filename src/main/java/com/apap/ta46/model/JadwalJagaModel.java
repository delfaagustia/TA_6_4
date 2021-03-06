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
	
	@NotNull
	@Column(name="id_dokter", nullable=false)
	private long idDokter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_paviliun", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PaviliunModel paviliun;
		
	@NotNull
	@Size(max=255)
	@Column(name="status_dokter", nullable=false)
	private String statusDokter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_waktu", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private WaktuModel waktu;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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

	public long getIdDokter() {
		return idDokter;
	}

	public void setIdDokter(long idDokter) {
		this.idDokter = idDokter;
	}

	public WaktuModel getWaktu() {
		return waktu;
	}

	public void setWaktu(WaktuModel waktu) {
		this.waktu = waktu;
	}
	
	
	
}
