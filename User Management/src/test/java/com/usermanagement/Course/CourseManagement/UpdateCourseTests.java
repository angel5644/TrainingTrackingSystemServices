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
import com.usermanagement.model.Courses;
import com.usermanagement.resource.CourseResource;
import com.usermanagement.model.CourseRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateCourseTests {

	@Mock
	CourseResource courseResourceMock;
	
	@Autowired
	CourseResource courseResource;
	
	/*Test cases
	 * 
	 * 1.- Course update successfull with many categories included
	 * 2.- Course update successfull when description is empty
	 * 3.- Course update not successfull when inserting 2 or more categories that doesnt exists
	 * 4.- Course update not successfull when inserting a course with a repeated name
	 * 5.- Course update not successfull when inserting a course with no categories included
	 * 6.- Course update not successfull when sending an course with an empty name and content
	 * 7.- Course update not successfull when inserting a course with a name longer than 50 characters
	 * 8.- Course update not successfull when inserting a coruse with a description longer than 500 characters
	 * 9.- Course update not successfull when inserting a course with a name with special chars
	 * 10.- Course update not successfull when id path param doesnt exist in the database
	 * 11.- Course update not successfull when id path param is invalid (negative number)
	 * 
	 */
	
	@Test
	public void updateCourseSuccessfully() {
		
		Integer id = 1;
		String name = "A course title";
		String description = "A course desription";
		Integer[] categories = {1,2,3};
		String content = "A course content";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);

		when(courseResourceMock.updateCourse(id,course)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		
		assertEquals(HttpStatus.OK, courseResourceMock.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseSuccessfullyWithEmptyDescription() {
		
		Integer id = 1;
		String name = "A course title";
		String description = "";
		Integer[] categories = {1,2,3};
		String content = "A course content";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);

		when(courseResourceMock.updateCourse(id,course)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		
		assertEquals(HttpStatus.OK, courseResourceMock.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseWhenAddingUnexistingCategories() {
		
		Integer id = 1;
		String name = "A course title";
		String description = "A course description";
		Integer[] categories = {1,2,3};
		String content = "A course content";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.CONFLICT, courseResource.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseWhenAddingARepeatedName() {
		Integer id = 1;
		String name = "A course2";
		String description = "A course description";
		Integer[] categories = {1,2,3};
		String content = "A course content";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.CONFLICT, courseResource.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseWithEmptyCategories() {
		Integer id = 1;
		String name = "A course title";
		String description = "A course description";
		Integer[] categories = {};
		String content = "A course content";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseWithEmptyNameAndContent() {
		Integer id = 1;
		String name = "";
		String description = "A course description";
		Integer[] categories = {1,2,3};
		String content = "";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseWithNameLongerThanExpected() {
		Integer id = 1;
		String name = "This is a course title that has more than fifty characters";
		String description = "A course description";
		Integer[] categories = {1,2,3};
		String content = "";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseWithDescriptionLongerThanExpected() {
		Integer id = 1;
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
		Integer[] categories = {1,2,3};
		String content = "";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseWithSpecialCharactersInTheName() {
		Integer id = 1;
		String name = "A course name with $%&";
		String description = "A course description";
		Integer[] categories = {1,2,3};
		String content = "";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.updateCourse(id,course).getStatusCode());
	}
	@Test
	public void updateCourseWithUnknownCourseId() {
		Integer id = 1000;
		String name = "A course name with $%&";
		String description = "A course description";
		Integer[] categories = {1,2,3};
		String content = "";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.updateCourse(id,course).getStatusCode());
	}
	
	@Test
	public void updateCourseWithInvalidCourseId() {
		Integer id = -10;
		String name = "A course name with $%&";
		String description = "A course description";
		Integer[] categories = {1,2,3};
		String content = "";

		CourseRequest course = new CourseRequest();
		course.setName(name);
		course.setDescription(description);
		course.setCategories(categories);
		course.setContent(content);
		
		assertEquals(HttpStatus.BAD_REQUEST, courseResource.updateCourse(id,course).getStatusCode());
	}
}
