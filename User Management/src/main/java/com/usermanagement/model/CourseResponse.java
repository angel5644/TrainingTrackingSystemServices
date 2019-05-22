package com.usermanagement.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="CourseResponse", description="API Model for the course response")
public class CourseResponse {

	@ApiModelProperty(position = 1, dataType="Integer", example = "1,2,3")
	private int id;
	@ApiModelProperty(position = 2, dataType="Integer", example = "Course name")
	private String name;
	@ApiModelProperty(position = 3, dataType="Integer", example = "Course description")
	private String description;
	@ApiModelProperty(position = 4, dataType="List", example = "[1,2,3]")
	private int[] categories;
	@ApiModelProperty(position = 5, dataType="String", example = "<div>HTML Content </div>")
	private String content;
	
}
