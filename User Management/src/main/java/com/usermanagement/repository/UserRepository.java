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
	
	//@Query("SELECT u FROM Users u WHERE u.id=(SELECT MAX(u1.id) FROM Users u1)")
	@Query("SELECT u FROM Users u")
	List<Users> findLastUserInserted();
	
	//When searchField is not specified
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Id ASC")
	List<Users> findOrderByIdASC(long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Id DESC")
	List<Users> findOrderByIdDESC(long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY first_Name ASC")
	List<Users> findOrderByFirstNameASC(long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY first_Name DESC")
	List<Users> findOrderByFirstNameDESC(long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY last_name ASC")
	List<Users> findOrderByLastNameASC(long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY last_name DESC")
	List<Users> findOrderByLastNameDESC(long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY email ASC")
	List<Users> findOrderByEmailASC(long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY email DESC")
	List<Users> findOrderByEmailDESC(long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY type ASC")
	List<Users> findOrderByTypeASC(long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY type DESC")
	List<Users> findOrderByTypeDESC(long lowerLimit, long upperLimit);
	//-----------------------------
	
	//When searchField ID is specified
	@Query("SELECT u FROM Users u WHERE Id = ?1")
	List<Users> findById(long searchValue);
	
	//When searchField FIRSTNAME is specified
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id ASC")
	List<Users> findByFirstNameOrderByIdASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id DESC")
	List<Users> findByFirstNameOrderByIdDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name ASC")
	List<Users> findByFirstNameOrderByFirstNameASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name DESC")
	List<Users> findByFirstNameOrderByFirstNameDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY last_name ASC")
	List<Users> findByFirstNameOrderByLastNameASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY last_name DESC")
	List<Users> findByFirstNameOrderByLastNameDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY email ASC")
	List<Users> findByFirstNameOrderByEmailASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY email DESC")
	List<Users> findByFirstNameOrderByEmailDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY type ASC")
	List<Users> findByFirstNameOrderByTypeASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY type DESC")
	List<Users> findByFirstNameOrderByTypeDESC(String searchValue,long lowerLimit, long upperLimit);
	//----------------------------------------
	
	//When searchField LASTNAME is specified
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id ASC")
	List<Users> findByLastNameOrderByIdASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id DESC")
	List<Users> findByLastNameOrderByIdDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name ASC")
	List<Users> findByLastNameOrderByFirstNameASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name DESC")
	List<Users> findByLastNameOrderByFirstNameDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY last_name ASC")
	List<Users> findByLastNameOrderByLastNameASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY last_name DESC")
	List<Users> findByLastNameOrderByLastNameDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY email ASC")
	List<Users> findByLastNameOrderByEmailASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY email DESC")
	List<Users> findByLastNameOrderByEmailDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY type ASC")
	List<Users> findByLastNameOrderByTypeASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE last_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY type DESC")
	List<Users> findByLastNameOrderByTypeDESC(String searchValue,long lowerLimit, long upperLimit);
	//----------------------------------------
	
	//When searchField EMAIL is specified
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id ASC")
	List<Users> findByEmailOrderByIdASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id DESC")
	List<Users> findByEmailOrderByIdDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name ASC")
	List<Users> findByEmailOrderByFirstNameASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name DESC")
	List<Users> findByEmailOrderByFirstNameDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY last_name ASC")
	List<Users> findByEmailOrderByLastNameASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY last_name DESC")
	List<Users> findByEmailOrderByLastNameDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY email ASC")
	List<Users> findByEmailOrderByEmailASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY email DESC")
	List<Users> findByEmailOrderByEmailDESC(String searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY type ASC")
	List<Users> findByEmailOrderByTypeASC(String searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE email = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY type DESC")
	List<Users> findByEmailOrderByTypeDESC(String searchValue,long lowerLimit, long upperLimit);
	//----------------------------------------
	
	//When searchField TYPE is specified
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id ASC")
	List<Users> findByTypeOrderByIdASC(Integer searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id DESC")
	List<Users> findByTypeOrderByIdDESC(Integer searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name ASC")
	List<Users> findByTypeOrderByFirstNameASC(Integer searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name DESC")
	List<Users> findByTypeOrderByFirstNameDESC(Integer searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY last_name ASC")
	List<Users> findByTypeOrderByLastNameASC(Integer searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY last_name DESC")
	List<Users> findByTypeOrderByLastNameDESC(Integer searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY email ASC")
	List<Users> findByTypeOrderByEmailASC(Integer searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY email DESC")
	List<Users> findByTypeOrderByEmailDESC(Integer searchValue,long lowerLimit, long upperLimit);
	
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY type ASC")
	List<Users> findByTypeOrderByTypeASC(Integer searchValue,long lowerLimit, long upperLimit);
	@Query("SELECT u FROM Users u WHERE type = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY type DESC")
	List<Users> findByTypeOrderByTypeDESC(Integer searchValue,long lowerLimit, long upperLimit);
	//----------------------------------------
	
	
	
	
	
	
	
	
	
	
	//@Query("SELECT u FROM USERS u WHERE ?1 = ?2 AND rownum BETWEEN ?3 AND ?4 ORDER BY ?5 ?6")

	/*
	@Query("SELECT u FROM Users u WHERE first_name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY first_name ASC")
	List<User> findUsersWithField(String searchValue, Integer lowerLimit,Integer upperLimit);
	
	//@Query("SELECT u FROM USERS u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY ?3 ?4")
	@Query("SELECT u FROM Users u WHERE rownum BETWEEN ?1 AND ?2 ORDER BY first_name ASC")
	List<User> findUsersWithoutField(Integer lowerLimit,Integer upperLimit);
	*/
}
