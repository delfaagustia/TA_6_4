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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "kamar")
public class KamarModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;

	@NotNull
	@Column(name = "id_pasien", nullable = false)
	private long idPasien;

	@NotNull
	@Column(name = "status", nullable = false)
	private int status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_paviliun", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PaviliunModel paviliun;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(long idPasien) {
		this.idPasien = idPasien;
	}

}
