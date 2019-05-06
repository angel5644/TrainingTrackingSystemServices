package com.usermanagement.service;

import java.util.List;
import com.usermanagement.model.Categories;
import com.usermanagement.model.FindCategoryResponse;

public interface CategoryManager {
	
	public List <Categories> getCategories();
	public String getResult();
	public Boolean createCategory(Categories theCategory);
	public Boolean validateFields(Categories theCategory);
	public Boolean updateCategory(Categories theCategory);
	public Boolean validateFields(String name, String description);
	public List <Categories> listCategories();
	public FindCategoryResponse findCategories(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec);
	public boolean validateSearchFields(String searchField, String searchValue, String orderBy, String orderType,
			String pageNo, String numberRec);
	
}
