package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.ta46.model.RequestPasienModel;


public interface RequestPasienDb extends JpaRepository<RequestPasienModel, Long>{
	
}
