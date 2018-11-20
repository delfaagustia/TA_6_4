package com.apap.ta46.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.ta46.model.UserRoleModel;


@Repository
public interface UserRoleDb extends JpaRepository<UserRoleModel, Long>{
	UserRoleModel findByusername (String username);	
}
