package com.usermanagement.service;

import java.util.List;
import com.usermanagement.model.Categories;

public interface CategoryManager {
	
	public List <Categories> getCategories();
	public String getResult();
	public void createUpdateCategory(Categories theCategory);
	public Boolean validateFields(Categories theCategory);
}
