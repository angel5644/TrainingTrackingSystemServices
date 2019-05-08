package com.usermanagement.service;

import java.util.List;

import com.usermanagement.model.CourseRequest;
import com.usermanagement.model.Courses;

public interface CourseManager {

	public Boolean createCourse(CourseRequest theCourse);
	public Boolean validateFields(CourseRequest theCourse);
	public List<Courses> getCourses();
	public String getResult();
	
}
