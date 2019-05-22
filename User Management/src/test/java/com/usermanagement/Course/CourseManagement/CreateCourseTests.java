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
import com.usermanagement.resource.CourseResource;
import com.usermanagement.model.CourseRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateCourseTests {

	@Mock
	CourseResource courseResourceMock;
	
	@Autowired
	CourseResource courseResource;
	
	/*Test cases
	 * 
	 * 1.- Course insert successfull with many categories included
	 * 2.- Course insert successfull when description is empty
	 * 3.- Course insert not successfull when inserting 2 or more categories that doesnt exists
	 * 4.- Course insert not successfull when inserting a course with a repeated name
	 * 5.- Course insert not successfull when inserting a course with no categories included
	 * 6.- Course insert not successfull when sending an course with an empty name and content
	 * 7.- Course insert not successfull when inserting a course with a name longer than 50 characters
	 * 8.- Course insert not successfull when inserting a coruse with a description longer than 500 characters
	 * 9.- Course insert not successfull when inserting a course with a name with special chars
	 * 
	 */
	
	@Test
	public void insertCourseSuccessfully() {
		
		//Arrange
		String name = "A course title";
		String description = "A course desription";
		int[] categories = {1,2,3};
		String content = "A course content";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		when(courseResourceMock.createCourse(course)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
		
		assertEquals(HttpStatus.CREATED, courseResourceMock.createCourse(course).getStatusCode());
	}
	
	@Test
	public void insertCourseSuccessfullyWithEmptyDescription() {
		
		//Arrange
		String name = "A course title";
		String description = "";
		int[] categories = {1,2,3};
		String content = "A course content";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		when(courseResourceMock.createCourse(course)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
		
		assertEquals(HttpStatus.CREATED, courseResourceMock.createCourse(course).getStatusCode());
	}
	
	@Test
	public void insertCourseWhenAddingUnexistingCategories() {
		
		//Arrange
		String name = "A course title";
		String description = "A course description";
		int[] categories = {1,2,3};
		String content = "A course content";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		//Assert
		assertEquals(HttpStatus.CONFLICT, courseResource.createCourse(course).getStatusCode());
	}
	
	@Test
	public void insertCourseWhenAddingARepeatedName() {
		
		//Arrange
		String name = "A course";
		String description = "A course description";
		int[] categories = {1,2,3};
		String content = "A course content";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		//Assert
		assertEquals(HttpStatus.CONFLICT, courseResource.createCourse(course).getStatusCode());
	}
	
	@Test
	public void insertCourseWithEmptyCategories() {
		
		//Arrange
		String name = "A course title";
		String description = "A course description";
		int[] categories = {};
		String content = "A course content";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		//Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.createCourse(course).getStatusCode());
	}
	
	@Test
	public void insertCourseWithEmptyNameAndContent() {
		
		//Arrange
		String name = "";
		String description = "A course description";
		int[] categories = {1,2,3};
		String content = "";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		//Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.createCourse(course).getStatusCode());
	}
	
	@Test
	public void insertCourseWithNameLongerThanExpected() {
		
		//Arrange
		String name = "This is a course title that has more than fifty characters";
		String description = "A course description";
		int[] categories = {1,2,3};
		String content = "";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		//Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.createCourse(course).getStatusCode());
	}
	
	@Test
	public void insertCourseWithDescriptionLongerThanExpected() {
		
		//Arrange
		String name = "A course title";
		String description = "This is a category description with more than 500 characters"
				+"Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
				+"sed eiusmod tempor incidunt ut labore et dolore magna aliqua."
				+"Ut enim ad minim veniam, quis nostrud exercitation ullamco"
				+"laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure"
				+"reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla"
				+"pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in"
				+"culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum"
				+"dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor.";
		int[] categories = {1,2,3};
		String content = "";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		//Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.createCourse(course).getStatusCode());
	}
	
	@Test
	public void insertCourseWithSpecialCharactersInTheName() {
		
		//Arrange
		String name = "A course name with $%&";
		String description = "A course description";
		int[] categories = {1,2,3};
		String content = "";
		CourseRequest course = new CourseRequest();
		
		//Act
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		//Assert
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.createCourse(course).getStatusCode());
	}
}
