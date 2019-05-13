package com.usermanagement.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.usermanagement.model.CourseRequest;
import com.usermanagement.service.CourseManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(maxAge = 3600)
@Api(value = "Course Management System")
@RestController
public class CourseResource {

	@Autowired
	private CourseManager courseManager;

	// Create Course
	@ApiOperation(value = "Creates a new course", response = ResponseEntity.class)
	@RequestMapping(value = "/course", method = RequestMethod.POST)
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
}
