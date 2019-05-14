package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.usermanagement.model.CatCourses;

public interface CatCourseRepository extends JpaRepository<CatCourses, Integer>{

	@Transactional
	@Modifying
	@Query("DELETE FROM CatCourses c WHERE idcourse = ?1")
	void deleteCategoriesByCourse(Integer id);
}
