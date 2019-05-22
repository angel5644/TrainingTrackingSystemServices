package com.usermanagement.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="CourseListResponse", description="API Model for the list course response")
public class CourseListResponse {

	@ApiModelProperty(position = 1, dataType="Integer", example = "1,2,3")
	private int totalRecords;
	@ApiModelProperty(position = 2, dataType="List")
	private CourseElement[] courses;
	
	public void setCourseElements(CourseResponse[] courseResponse){
		CourseElement[] courseElement = new CourseElement[courseResponse.length];
		
		for(int i=0; i < courseResponse.length; i++){
			courseElement[i] = new CourseElement();
			courseElement[i].setId(courseResponse[i].getId());
			courseElement[i].setName(courseResponse[i].getName());
			courseElement[i].setCategories(courseResponse[i].getCategories());
		}
		
		this.courses = courseElement;
	}
	
}

@Data
class CourseElement{
	@ApiModelProperty(position = 1, dataType="Integer", example = "1,2,3")
	private int id;
	@ApiModelProperty(position = 2, dataType="String", example = "Course name")
	private String name;
	@ApiModelProperty(position = 4, dataType="List", example = "[1,2,3]")
	private int[] categories;
}
