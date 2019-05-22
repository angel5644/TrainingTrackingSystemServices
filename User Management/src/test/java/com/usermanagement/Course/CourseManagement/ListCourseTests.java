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
public class ListCourseTests {

	@Mock
	CourseResource courseResourceMock;

	@Autowired
	CourseResource courseResource;

	/*
	 * Test cases
	 * 
	 * 1.- List courses successfully 
	 * 2.- List courses when user-id header is not defined 
	 * 3.- List courses when user-id header is not an integer 
	 * 4.- List courses when user-id header doesn't exist in the database 
	 * 5.- List courses when user-id header doesn't have enough privileges (Not an admin
	 * nor trainer)
	 * 
	 */

	@Test
	public void listCoursesSuccessfully() {
		// Arrange
		String userId = "6";

		// Act

		// Assert
		assertEquals(HttpStatus.OK, courseResource.listCourses(userId).getStatusCode());
	}
	
	@Test
	public void listCoursesWhenUserIdIsNotDefined() {
		// Arrange
		String userId = null;

		// Act

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.listCourses(userId).getStatusCode());
	}
	
	@Test
	public void listCoursesWhenUserIdIsInvalid() {
		// Arrange
		String userId = "Hi";

		// Act

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.listCourses(userId).getStatusCode());
	}
	
	@Test
	public void listCoursesWhenUserIdDoesntExist() {
		// Arrange
		String userId = "1";

		// Act

		// Assert
		assertEquals(HttpStatus.CONFLICT, courseResource.listCourses(userId).getStatusCode());
	}
	
	@Test
	public void listCoursesWhenUserDoesntHaveEnoughPrivileges() {
		// Arrange
		String userId = "5";

		// Act

		// Assert
		assertEquals(HttpStatus.UNAUTHORIZED, courseResource.listCourses(userId).getStatusCode());
	}
}
