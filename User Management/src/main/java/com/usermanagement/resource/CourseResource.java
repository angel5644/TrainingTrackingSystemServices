package com.usermanagement.resource;

import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.usermanagement.model.CourseRequest;
import com.usermanagement.model.CourseResponse;
import com.usermanagement.model.Users;
import com.usermanagement.model.CourseListResponse;
import com.usermanagement.service.CourseManager;
import com.usermanagement.service.UserManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin(maxAge = 3600)
@Api(value = "Course Management System")
@RestController
public class CourseResource {

	@Autowired
	private CourseManager courseManager;

	@Autowired
	private UserManager userManager;

	// Create Course
	@ApiOperation(value = "Creates a new course", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Course created successfully", response = CourseResponse.class),
			@ApiResponse(code = 400, message = "Bad Request"), 
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/course", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<?> createCourse(@ModelAttribute("CourseRequest") CourseRequest theCourse) {

		theCourse.setName(theCourse.getName().trim().toUpperCase());
		Boolean isOk = courseManager.validateFields(theCourse);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
				public final int status_code = 400;
				public final String error_msg = "The following error(s) occurred: " + courseManager.getResult();
			});
		} else {

			CourseResponse courseResponse = courseManager.createCourse(theCourse);

			if (courseResponse == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new Object() {
					public final int status_code = 409;
					public final String error_msg = "The following error(s) occurred: " + courseManager.getResult();
				});
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
			}
		}
	}

	// Update Course
	@ApiOperation(value = "Updates an existing course", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Course updated successfully", response = CourseResponse.class),
			@ApiResponse(code = 400, message = "Bad Request"), 
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> updateCourse(
			@ApiParam(value = "Id of the course to be edited", required = true) @PathVariable("id") final int id,
			@ModelAttribute("CourseRequest") CourseRequest theCourse) {

		theCourse.setName(theCourse.getName().trim().toUpperCase());
		Boolean isOk = courseManager.validateFields(theCourse);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
				public final int status_code = 400;
				public final String error_msg = "The following error(s) occurred: " + courseManager.getResult();
			});
		} else {

			CourseResponse courseResponse = courseManager.updateCourse(id, theCourse);

			if (courseResponse == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new Object() {
					public final int status_code = 409;
					public final String error_msg = "The following error(s) occurred: " + courseManager.getResult();
				});
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
			}
		}
	}

	// List Courses
	@ApiOperation(value = "Returns all the courses created in the database (Must be a trainer or admin)", response = ResponseEntity.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = CourseListResponse.class),
			@ApiResponse(code = 400, message = "Bad Request"), 
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/course", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> listCourses(@RequestHeader(value = "user-id", required = true) String userId) {

		boolean isUserIdInteger = true;
		try {
			Integer.parseInt(userId);
		} catch (TypeMismatchException e) {
			isUserIdInteger = false;
		} catch (NumberFormatException e) {
			isUserIdInteger = false;
		}

		if (!isUserIdInteger) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
				public final int status_code = 400;
				public final String error_msg = "The following error(s) occurred: The Id parameter is not a valid integer. ";
			});
		} else {
			Users user = userManager.findById(Integer.parseInt(userId));

			if (user == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new Object() {
					public final int status_code = 409;
					public final String error_msg = "The following error(s) occurred: The user doesn't exist in the database. ";
				});
			} else {
				CourseListResponse courses = courseManager.getCourses(user);
				if (courses == null) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Object() {
						public final int status_code = 401;
						public final String error_msg = "The following error(s) occurred: The user doesn't have enough privileges to view the courses, must be at least a trainer. ";
					});
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(courses);
				}
			}
		}
	}

	// View Course
	@ApiOperation(value = "Returns the details of the course specified by it's id", response = ResponseEntity.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = CourseListResponse.class),
			@ApiResponse(code = 400, message = "Bad Request"), 
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> viewCourse(@PathVariable("id") final String courseId) {

		boolean isCourseIdInteger = true;
		try {
			Integer.parseInt(courseId);
		} catch (TypeMismatchException e) {
			isCourseIdInteger = false;
		} catch (NumberFormatException e) {
			isCourseIdInteger = false;
		}

		if (!isCourseIdInteger) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
				public final int status_code = 400;
				public final String error_msg = "The following error(s) occurred: The Id parameter is not a valid integer. ";
			});
		} else {
			if (Integer.parseInt(courseId) < 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
					public final int status_code = 400;
					public final String error_msg = "The following error(s) occurred: The course ID entered must be greater than 0. ";
				});
			} else {
				CourseResponse courseDetails = courseManager.getCourse(Integer.parseInt(courseId));
				if (courseDetails == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
						public final int status_code = 404;
						public final String error_msg = "The following error(s) occurred: " + courseManager.getResult();
					});
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(courseDetails);
				}
			}
		}
	}

	// Delete Course
	@ApiOperation(value = "Delete the course specified by it's ID. ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "OK, NO CONTENT", response = CourseListResponse.class),
			@ApiResponse(code = 400, message = "Bad Request"), 
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteCourse(@RequestHeader(value = "user-id", required = true) String userId,
			@PathVariable("id") final String courseId) {
		
		boolean isOk = courseManager.validateUserIdCourseId(userId,courseId);
		
		if(!isOk){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
				public final int status_code = 400;
				public final String error_msg = "The following error(s) occurred: " + courseManager.getResult();
			});
		}
		else{
			Users user = userManager.findById(Integer.parseInt(userId));

			if (user == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new Object() {
					public final int status_code = 409;
					public final String error_msg = "The following error(s) occurred: The user doesn't exist in the database. ";
				});
			} else {
				if (!courseManager.deleteCourse(Integer.parseInt(courseId),user)) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Object() {
						public final int status_code = 401;
						public final String error_msg = "The following error(s) occurred: The user doesn't have enough privileges to delete a course. It must be an admin. ";
					});
				} else {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
				}
			}
		}
	}
}
