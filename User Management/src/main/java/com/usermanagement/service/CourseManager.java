package com.usermanagement.service;

import com.usermanagement.model.CourseRequest;
import com.usermanagement.model.CourseResponse;

public interface CourseManager {

	public CourseResponse createCourse(CourseRequest theCourse);
	public CourseResponse updateCourse(int id, CourseRequest theCourse);
	public boolean validateFields(CourseRequest theCourse);
	public String getResult();
	
}
