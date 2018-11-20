package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.ta46.model.PaviliunModel;

@Repository
public interface PaviliunDB extends JpaRepository<PaviliunModel, Long>{
}
