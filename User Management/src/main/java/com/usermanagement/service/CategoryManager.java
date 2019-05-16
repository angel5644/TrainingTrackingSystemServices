package com.usermanagement.service;

import java.util.List;
import com.usermanagement.model.Category;
import com.usermanagement.model.FindCategoryResponse;

public interface CategoryManager {
	
	public List <Category> getCategories();
	public String getResult();
	public boolean createCategory(Category theCategory);
	public boolean validateFields(Category theCategory);
	public boolean updateCategory(Category theCategory);
	public boolean validateFields(String name, String description);
	public List <Category> listCategories();
	public FindCategoryResponse findCategories(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec);
	public boolean validateSearchFields(String searchField, String searchValue, String orderBy, String orderType,
			String pageNo, String numberRec);
	
}
