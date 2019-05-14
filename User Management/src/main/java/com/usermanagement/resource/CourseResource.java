package com.usermanagement.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.usermanagement.model.CourseRequest;
import com.usermanagement.model.CourseResponse;
import com.usermanagement.service.CourseManager;
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

	// Create Course
	@ApiOperation(value = "Creates a new course", response = ResponseEntity.class)
	@ApiResponses(value = { 
            @ApiResponse(code = 201, message = "Course created successfully", response = CourseResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict"), 
            @ApiResponse(code = 500, message = "Internal Server Error") 
            })
	@RequestMapping(value = "/course", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<?> createCourse(@ModelAttribute("CourseRequest") CourseRequest theCourse) {

		theCourse.setName(theCourse.getName().trim().toUpperCase());
		Boolean isOk = courseManager.validateFields(theCourse);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("The following error(s) occurred: " + courseManager.getResult());
		} else {
			if (!courseManager.createCourse(theCourse)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("The following error(s) occurred: " + courseManager.getResult());
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(courseManager.getCourseResponse());
			}
		}
	}
	
		//Update Course
		@ApiOperation(value = "Updates an existing course", response = ResponseEntity.class)
		@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Course updated successfully", response = CourseResponse.class),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 409, message = "Conflict"), 
	            @ApiResponse(code = 500, message = "Internal Server Error") 
	            })
		@RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
		@ResponseStatus(HttpStatus.OK)
		@ResponseBody
		public ResponseEntity<?> updateCourse(
				@ApiParam(value = "Id of the course to be edited", required = true)
				@PathVariable("id") final Integer id,
				@ModelAttribute("CourseRequest") CourseRequest theCourse) {

			theCourse.setName(theCourse.getName().trim().toUpperCase());
			Boolean isOk = courseManager.validateFields(theCourse);

			if (!isOk) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("The following error(s) occurred: " + courseManager.getResult());
			} else {
				if (!courseManager.updateCourse(id,theCourse)) {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body("The following error(s) occurred: " + courseManager.getResult());
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(courseManager.getCourseResponse());
				}
			}
		}
}
