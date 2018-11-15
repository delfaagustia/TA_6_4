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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="paviliun")
public class PaviliunModel implements Serializable {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama_paviliun", nullable=false)
	private String namaPaviliun;
	
	@NotNull
	@Size(max=255)
	@Column(name="tipe_pasien", nullable=false)
	private String tipePasien;
	
	@NotNull
	@Column(name="status", nullable=false)
	private int status;
	
	@OneToMany(mappedBy="paviliun", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<KamarModel> kamarList;
	
	@OneToMany(mappedBy="paviliun", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<JadwalJagaModel> jadwalJagaList;
}
