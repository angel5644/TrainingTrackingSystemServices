package com.usermanagement.model;

import java.util.List;

import lombok.Data;

@Data
public class FindCategoryResponse {

	private int totalRecords;
	private List<Category> categories;
    
}