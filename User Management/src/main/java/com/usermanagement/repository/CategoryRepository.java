package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
	Optional<Categories> findById(Integer id);
	List<Categories> findByName(String name);
	List<Categories> findByDescription(String description);
}
