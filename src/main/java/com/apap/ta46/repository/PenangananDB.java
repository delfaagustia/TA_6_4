package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.ta46.model.PemeriksaanModel;
import java.lang.Long;
import java.util.List;

@Repository
public interface PenangananDB extends JpaRepository<PemeriksaanModel, Long>{
	List<PemeriksaanModel> findAllByIdPasien(long idPasien);
}
