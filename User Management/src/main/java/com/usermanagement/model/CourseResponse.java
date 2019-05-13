package com.usermanagement.model;

import lombok.Data;

@Data
public class CourseResponse {

	private Integer id;
	private String name;
	private String description;
	private Integer[] categories;
	private String content;
	
}
