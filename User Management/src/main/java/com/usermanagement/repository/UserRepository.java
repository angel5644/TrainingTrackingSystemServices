package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usermanagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByFirstName(String first_name);
	List<User> findByLastName(String last_name);
	List<User> findByEmail(String email);
	List<User> findByType(Integer type);
	Optional<User> findById(Integer id);
	
	@Query("SELECT * FROM USERS WHERE ?1 = ?2 AND rownum BETWEEN ?3 AND ?4 ORDER BY ?5 ?6")
	List<User> findUsersWithField(String searchField, String searchValue, Integer lowerLimit,Integer upperLimit,String orderBy,String orderType);
	
	@Query("SELECT * FROM USERS WHERE rownum BETWEEN ?1 AND ?2 ORDER BY ?3 ?4")
	List<User> findUsersWithoutField(Integer lowerLimit,Integer upperLimit,String orderBy,String orderType);
}
