package com.usermanagement.Course.CourseManagement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.usermanagement.model.CourseRequest;
import com.usermanagement.resource.CourseResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewCourseTest {

	@Mock
	CourseResource courseResourceMock;

	@Autowired
	CourseResource courseResource;

	/*
	 * Test cases
	 * 
	 * 1.- View course successfully
	 * 2.- View course when the ID is not an integer
	 * 3.- View course when the ID is not a valid number
	 * 4.- View course when the course ID doesn't exist in the database
	 * 
	 */

	@Test
	public void viewCourseSuccessfully() {
		// Arrange
		String courseId = "2";

		// Act

		// Assert
		assertEquals(HttpStatus.OK, courseResource.viewCourse(courseId).getStatusCode());
	}
	
	@Test
	public void viewCourseWhenIdIsNotInteger() {
		// Arrange
		String courseId = "Hello";

		// Act

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.viewCourse(courseId).getStatusCode());
	}
	
	@Test
	public void viewCourseWhenIdIsNotValidNumber() {
		// Arrange
		String courseId = "-14";

		// Act

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.viewCourse(courseId).getStatusCode());
	}
	
	@Test
	public void viewCourseWhenIdDoesntExist() {
		// Arrange
		String courseId = "2000";

		// Act

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, courseResource.viewCourse(courseId).getStatusCode());
	}
}
