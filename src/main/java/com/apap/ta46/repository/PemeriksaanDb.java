package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.ta46.model.PemeriksaanModel;

@Repository
public interface PemeriksaanDb extends JpaRepository<PemeriksaanModel, Long> {

}
