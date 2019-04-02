package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usermanagement.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	List<Users> findByFirstName(String first_name);
	List<Users> findByLastName(String last_name);
	List<Users> findByEmail(String email);
	List<Users> findByType(Integer type);
	Optional<Users> findById(Integer id);
	
	//When searchField is not specified
	@Query("SELECT u FROM Users u WHERE Id = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id ASC")
	List<Users> findOrderByIdASC(long lowerLimit, long upperLimit);
	//-----------------------------
	
	@Query("SELECT u FROM Users u WHERE Id = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id ASC")
	List<Users> findByIdOrderByIdASC(long searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE Id = ?1")
	List<Users> findById(long searchValue);
	
	//@Query("SELECT u FROM USERS u WHERE ?1 = ?2 AND rownum BETWEEN ?3 AND ?4 ORDER BY ?5 ?6")

	/*
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name ASC")
	List<User> findUsersWithField(String searchValue, Integer lowerLimit,Integer upperLimit);
	
	//@Query("SELECT u FROM USERS u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY ?3 ?4")
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY first_name ASC")
	List<User> findUsersWithoutField(Integer lowerLimit,Integer upperLimit);
	*/
}
