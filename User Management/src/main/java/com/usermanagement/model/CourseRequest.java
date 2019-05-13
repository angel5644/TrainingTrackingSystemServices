package com.usermanagement.model;

import lombok.Data;

@Data
public class CourseRequest {

	private String name;
	private String description;
	private Integer[] categories;
	private String content;
	
}
