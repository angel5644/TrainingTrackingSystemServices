package com.usermanagement.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="CourseRequest", description="API Model for the course request")
public class CourseRequest {

	@ApiModelProperty(position = 2, dataType="Integer", example = "Course name", required = true)
	private String name;
	@ApiModelProperty(position = 3, dataType="Integer", example = "Course description", required = false)
	private String description;
	@ApiModelProperty(position = 4, dataType="List", example = "[1,2,3]", required = true)
	private int[] categories;
	@ApiModelProperty(position = 5, dataType="String", example = "<div>HTML Content </div>", required = true)
	private String content;
	
}
