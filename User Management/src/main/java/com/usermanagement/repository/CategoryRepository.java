package com.usermanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usermanagement.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
	List<Categories> findByName(String name);
	List<Categories> findByDescription(String description);
}
