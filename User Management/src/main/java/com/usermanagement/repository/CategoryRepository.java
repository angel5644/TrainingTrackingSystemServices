package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}
