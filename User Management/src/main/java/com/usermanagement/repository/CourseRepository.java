package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.usermanagement.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Integer> {

	List<Courses> findByName(String name);
	Optional<Courses> findById(Integer id);
	@Query("SELECT c FROM Courses c WHERE name = ?1 AND Id <> ?2")
	List<Courses> findDuplicate(String name, Integer id);
}
