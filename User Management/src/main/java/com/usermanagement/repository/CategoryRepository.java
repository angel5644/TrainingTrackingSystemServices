package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usermanagement.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByName(String name);

	List<Category> findByDescription(String description);

	Optional<Category> findById(int id);

	@Query("SELECT c FROM Category c WHERE name = ?1 AND Id <> ?2")
	List<Category> findDuplicate(String name, Integer id);
	
	long count();
	// When searchField is not specified
	@Query("SELECT c FROM Category c WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Id ASC")
	List<Category> findOrderByIdASC(long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Category c WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Id DESC")
	List<Category> findOrderByIdDESC(long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Category c WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Name ASC")
	List<Category> findOrderByNameASC(long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Category c WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Name DESC")
	List<Category> findOrderByNameDESC(long lowerLimit, long upperLimit);
	// -----------------------------

	// When searchField ID is specified
	@Query("SELECT c FROM Category c WHERE Id = ?1")
	List<Category> findById(long searchValue);
	// -----------------------------

	// When searchField NAME is specified
	long countByName(String searchValue);
	@Query("SELECT c FROM Category c WHERE name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id ASC")
	List<Category> findByNameOrderByIdASC(String searchValue, long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Category c WHERE name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id DESC")
	List<Category> findByNameOrderByIdDESC(String searchValue, long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Category c WHERE name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY name ASC")
	List<Category> findByNameOrderByNameASC(String searchValue, long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Category c WHERE name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY name DESC")
	List<Category> findByNameOrderByNameDESC(String searchValue, long lowerLimit, long upperLimit);
	// ------------------------------
}
