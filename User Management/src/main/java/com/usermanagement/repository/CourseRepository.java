package com.usermanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Integer> {

	List<Courses> findByName(String name);
}
