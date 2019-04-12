package com.usermanagement.service;

import java.security.InvalidParameterException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.model.Categories;
import com.usermanagement.repository.CategoryRepository;

@Service
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
	public Boolean createUpdateCategory(Categories theCategory) throws DuplicateKeyException{
		result = "";
		Boolean isOk = true;
		try{
			if(categoryRepository.findByName(theCategory.getName()).size() > 0){
				result = "There is already a category with that name. ";
				isOk = false;
			}
			else{
				categoryRepository.save(theCategory);
			}
			
			if(!isOk){
				result = "The following error(s) occurred: " + result;
				throw new DuplicateKeyException(result); 
			}
		}
		catch(DuplicateKeyException ex){
			System.out.println(ex);
			return false;
		}
		return isOk;
	}
	
	public Boolean validateFields(Categories theCategory) throws InvalidParameterException{
		Boolean isOk = true;
		result = "";

		try {
			if (theCategory == null) {
				result = "Invalid Category type. ";
				return false;
				
			} else {
				String name = theCategory.getName();
				String description = theCategory.getDescription();
				
				if (StringUtils.isBlank(name)) {
					result += "The 'name' field is empty or missing. ";
					isOk = false;
				}
				else{
					if(name.length() > 50){
						result += "The 'name' field has more than 50 characters. ";
						isOk = false;
					}
				}
				
				if (StringUtils.isBlank(description)) {
					result += "The 'description' field is empty or missing. ";
					isOk = false;
				}
				else{
					if(description.length() > 500){
						result += "The 'description' field has more than 500 characters. ";
						isOk = false;
					}
				}	
			}
			
			if(!isOk){
				result = "The following error occurred: " + result;
				throw new InvalidParameterException(result); 
			}
			
		} catch (InvalidParameterException ex) {
			System.out.println(ex);
			return false;
		}
		return isOk;
	}
	
	public String getResult(){
		return this.result;
	}
}
