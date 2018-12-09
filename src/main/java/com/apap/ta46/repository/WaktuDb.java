package com.apap.ta46.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.ta46.model.WaktuModel;

@Repository
public interface WaktuDb extends JpaRepository<WaktuModel, Long> {
	
}
