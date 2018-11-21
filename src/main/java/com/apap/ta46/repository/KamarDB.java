package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.Long;
import com.apap.ta46.model.KamarModel;

@Repository
public interface KamarDB extends JpaRepository<KamarModel, Long>{
	KamarModel findByIdPasien(long idPasien);
}
