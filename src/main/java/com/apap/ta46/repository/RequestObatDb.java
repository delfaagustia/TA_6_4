package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.ta46.model.PemeriksaanModel;
import com.apap.ta46.model.RequestObatModel;

@Repository
public interface RequestObatDb extends JpaRepository<RequestObatModel, Long> {
	RequestObatModel findById(long id);

}
