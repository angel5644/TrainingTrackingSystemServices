package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.usermanagement.model.CatCourse;

public interface CatCourseRepository extends JpaRepository<CatCourse, Integer>{

	@Transactional
	@Modifying
	@Query("DELETE FROM CatCourse c WHERE idcourse = ?1")
	int deleteCategoriesByCourse(int id);
}
