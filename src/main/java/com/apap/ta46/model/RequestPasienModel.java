package com.apap.ta46.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="request_pasien")
public class RequestPasienModel implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Column(name="assign", nullable=false)
	private int assign;

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
	
	
	
	
}
