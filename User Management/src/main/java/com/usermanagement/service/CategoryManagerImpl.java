package com.usermanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.model.Categories;
import com.usermanagement.repository.CategoryRepository;

@Service
public class CategoryManagerImpl implements CategoryManager {

	@Autowired
    CategoryRepository categoryRepository;
	
	@Override
	@Transactional
	public List <Categories> getCategories(){
		return categoryRepository.findAll();
	}
	
	@Override
	@Transactional
	public List <Categories> listCategories(){
		return null;
	}
}