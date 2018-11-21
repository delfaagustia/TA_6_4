package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.ta46.model.JadwalJagaModel;
import java.lang.Long;

@Repository
public interface JadwalJagaDB extends JpaRepository<JadwalJagaModel, Long>{

}
