package com.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.model.Categories;
import com.usermanagement.model.Users;
import com.usermanagement.repository.CategoryRepository;

public class CategoryManagerImpl implements CategoryManager {

	@Autowired
    CategoryRepository categoryRepository;
	
	private String result;
	
	@Override
	@Transactional
	public List <Categories> getCategories(){
		return categoryRepository.findAll();
	}
	
	@Override
	@Transactional
	public void createUpdateCategory(Categories theCategory){
		categoryRepository.save(theCategory);
	}
	
	public Boolean validateFields(Categories theCategory){
		//MISSING CODE PENDING
		return false;
	}
	
	public String getResult(){
		return this.result;
	}
}
