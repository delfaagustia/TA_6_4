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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="request_pasien")
public class RequestPasienModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Column(name="assign", nullable=false)
	private int assign;
	
	@OneToOne(fetch=FetchType.LAZY)
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

	public int getAssign() {
		return assign;
	}

	public void setAssign(int assign) {
		this.assign = assign;
	}

	public PasienModel getPasien() {
		return pasien;
	}

	public void setPasien(PasienModel pasien) {
		this.pasien = pasien;
	}
	
	
	
	
}
