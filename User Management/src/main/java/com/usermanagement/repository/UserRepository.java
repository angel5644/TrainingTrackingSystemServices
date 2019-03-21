package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.usermanagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
