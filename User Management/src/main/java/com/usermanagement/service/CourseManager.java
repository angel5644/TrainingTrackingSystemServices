package com.usermanagement.service;

import com.usermanagement.model.CourseListResponse;
import com.usermanagement.model.CourseRequest;
import com.usermanagement.model.CourseResponse;
import com.usermanagement.model.Users;

public interface CourseManager {

	public boolean deleteCourse(int courseId, Users theUser);
	public CourseListResponse getCourses(Users theUser);
	public CourseResponse getCourse(int courseId);
	public CourseResponse createCourse(CourseRequest theCourse);
	public CourseResponse updateCourse(int id, CourseRequest theCourse);
	public boolean validateFields(CourseRequest theCourse);
	public boolean validateUserIdCourseId(String userId, String courseId);
	public String getResult();
	
}
