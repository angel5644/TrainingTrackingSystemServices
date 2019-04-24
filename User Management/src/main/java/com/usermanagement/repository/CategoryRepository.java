package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usermanagement.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
	Optional<Categories> findById(Integer id);
	
	@Query("SELECT c FROM Categories c WHERE name = ?1 AND Id <> ?2")
	List<Categories> findDuplicate(String name,Integer id);
	
	List<Categories> findByDescription(String description);
}
