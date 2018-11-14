package com.apap.ta46.model;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name="pemeriksaan")
public class PemeriksaanModel implements Serializable{
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(max=255)
	@Column(name="pemeriksaan", nullable=false)
	private String pemeriksaan;
	
	@NotNull
	@Column(name="waktu", nullable=false)
	private Date waktu;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_dokter", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private DokterModel dokter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pasien", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PasienModel pasien;
	
	@OneToMany(mappedBy="pemeriksaan", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<RequestObatModel> requestObatList;
}
