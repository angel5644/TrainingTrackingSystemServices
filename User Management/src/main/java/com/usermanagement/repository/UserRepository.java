package com.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByFirstName(String first_name);
	List<User> findByLastName(String last_name);
	List<User> findByEmail(String email);
	List<User> findByType(Integer type);
}
