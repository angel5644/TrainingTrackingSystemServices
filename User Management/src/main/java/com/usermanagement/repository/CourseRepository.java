package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.usermanagement.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByName(String name);
	Optional<Course> findById(Integer id);
	@Query("SELECT c FROM Course c WHERE name = ?1 AND Id <> ?2")
	List<Course> findDuplicate(String name, Integer id);
}
