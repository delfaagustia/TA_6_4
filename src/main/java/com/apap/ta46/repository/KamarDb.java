package com.apap.ta46.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.ta46.model.KamarModel;

@Repository
public interface KamarDb extends JpaRepository<KamarModel, Long> {
	KamarModel findById(long id);
	KamarModel findByStatus(int status);
	List<KamarModel> findByIdPasienNot(long idPasien);
	KamarModel findByIdPasien(long idPasien);
	List<KamarModel> findByIdPasienNotAndStatus(long idPasien, int status);
}
