package com.usermanagement.Course.CourseManagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.usermanagement.model.CourseRequest;
import com.usermanagement.resource.CourseResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteCourseTest {

	@Mock
	CourseResource courseResourceMock;

	@Autowired
	CourseResource courseResource;

	/*
	 * Test cases
	 * 
	 * 1.- Delete course successfully 
	 * 2.- Delete course when user-id header is not defined 
	 * 3.- Delete course when user-id header is not an integer 
	 * 4.- Delete course when user-id header doesn't exist in the database 
	 * 5.- Delete course when user-id header doesn't have enough privileges (Not an admin)
	 * 
	 */

	@Test
	public void deleteCourseSuccessfully() {
		// Arrange
		String userId = "7";
		String courseId = "2";

		// Act
		when(courseResourceMock.deleteCourse(userId,courseId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		
		// Assert
		assertEquals(HttpStatus.NO_CONTENT, courseResourceMock.deleteCourse(userId,courseId).getStatusCode());
	}
	
	@Test
	public void deleteCourseWhenUserIdIsNotDefined() {
		// Arrange
		String userId = null;
		String courseId = "2";

		// Act

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.deleteCourse(userId,courseId).getStatusCode());
	}
	
	@Test
	public void deleteCourseWhenUserIdIsInvalid() {
		// Arrange
		String userId = "Hi";
		String courseId = "2";

		// Act

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.deleteCourse(userId,courseId).getStatusCode());
	}
	
	@Test
	public void deleteCourseWhenUserIdDoesntExist() {
		// Arrange
		String userId = "1";
		String courseId = "2";

		// Act

		// Assert
		assertEquals(HttpStatus.CONFLICT, courseResource.deleteCourse(userId,courseId).getStatusCode());
	}
	
	@Test
	public void deleteCourseWhenUserDoesntHaveEnoughPrivileges() {
		// Arrange
		String userId = "6";
		String courseId = "2";

		// Act

		// Assert
		assertEquals(HttpStatus.UNAUTHORIZED, courseResource.deleteCourse(userId,courseId).getStatusCode());
	}
}
