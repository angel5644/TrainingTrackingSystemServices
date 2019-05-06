package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usermanagement.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
	List<Categories> findByName(String name);

	List<Categories> findByDescription(String description);

	Optional<Categories> findById(Integer id);

	@Query("SELECT c FROM Categories c WHERE name = ?1 AND Id <> ?2")
	List<Categories> findDuplicate(String name, Integer id);
	
	long count();
	// When searchField is not specified
	@Query("SELECT c FROM Categories c WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Id ASC")
	List<Categories> findOrderByIdASC(long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Categories c WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Id DESC")
	List<Categories> findOrderByIdDESC(long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Categories c WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Name ASC")
	List<Categories> findOrderByNameASC(long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Categories c WHERE rownum BETWEEN ?1 AND ?2 ORDER BY Name DESC")
	List<Categories> findOrderByNameDESC(long lowerLimit, long upperLimit);
	// -----------------------------

	// When searchField ID is specified
	@Query("SELECT c FROM Categories c WHERE Id = ?1")
	List<Categories> findById(long searchValue);
	// -----------------------------

	// When searchField NAME is specified
	long countByName(String searchValue);
	@Query("SELECT c FROM Categories c WHERE name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id ASC")
	List<Categories> findByNameOrderByIdASC(String searchValue, long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Categories c WHERE name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY Id DESC")
	List<Categories> findByNameOrderByIdDESC(String searchValue, long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Categories c WHERE name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY name ASC")
	List<Categories> findByNameOrderByNameASC(String searchValue, long lowerLimit, long upperLimit);

	@Query("SELECT c FROM Categories c WHERE name = ?1 AND rownum BETWEEN ?2 AND ?3 ORDER BY name DESC")
	List<Categories> findByNameOrderByNameDESC(String searchValue, long lowerLimit, long upperLimit);
	// ------------------------------
}
