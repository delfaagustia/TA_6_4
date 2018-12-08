package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.ta46.model.JadwalJagaModel;
import java.lang.Long;
import java.util.List;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long>{
	List<JadwalJagaModel> findAllByIdDokter(long idDokter);
}
