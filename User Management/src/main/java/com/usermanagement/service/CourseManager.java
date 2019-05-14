package com.usermanagement.service;

import com.usermanagement.model.CourseRequest;
import com.usermanagement.model.CourseResponse;

public interface CourseManager {

	public Boolean createCourse(CourseRequest theCourse);
	public Boolean updateCourse(Integer id, CourseRequest theCourse);
	public Boolean validateFields(CourseRequest theCourse);
	public CourseResponse getCourseResponse();
	public String getResult();
	
}
