package com.usermanagement.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.model.Categories;
import com.usermanagement.model.FindCategoryResponse;
import com.usermanagement.repository.CategoryRepository;

@Service
public class CategoryManagerImpl implements CategoryManager {

	@Autowired
    CategoryRepository categoryRepository;
	
	private String result;
	private long totalRecordsFound;
	
	@Override
	@Transactional
	public List <Categories> getCategories(){
		return categoryRepository.findAll();
	}
	
	@Override
	@Transactional
	public List <Categories> listCategories(){
		return categoryRepository.findAll();
	}
	
	@Override
	@Transactional
	public Boolean createCategory(Categories theCategory) throws DuplicateKeyException{
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
					else{
						Pattern specialChars = Pattern.compile("^[A-Za-z0-9 ]+$");
						Matcher hasNotSpecialChars = specialChars.matcher(name);

						if (!hasNotSpecialChars.find()) {
							result += "The 'name' field doesn't accept special characters. ";
							isOk = false;
						}
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
	
	@Override
	@Transactional
	public Boolean updateCategory(Categories theCategory) throws DuplicateKeyException {
		result = "";
		Boolean isOk = true;
		try {
			Categories category = (categoryRepository.findById(theCategory.getId()).isPresent())
					? categoryRepository.findById(theCategory.getId()).get() : null;

			if (category == null) {
				result = " The category to be edited was not found in the database. ";
				isOk = false;
			} else {
				if (categoryRepository.findDuplicate(theCategory.getName(), theCategory.getId()).size() > 0) {
					result = " There is already a category with that name. ";
					isOk = false;
				} else {
					if (!StringUtils.isEmpty(theCategory.getName().trim().toUpperCase())) {
						category.setName(theCategory.getName().trim().toUpperCase());
					}
					if (!StringUtils.isEmpty(theCategory.getDescription().trim().toUpperCase())) {
						category.setDescription(theCategory.getDescription().trim().toUpperCase());
					}
					categoryRepository.save(category);
				}
			}

			if (!isOk) {
				result = "The following error(s) occurred: " + result;
				throw new DuplicateKeyException(result);
			}
		} catch (DuplicateKeyException ex) {
			System.out.println(ex);
			return false;
		}
		return isOk;
	}

	public Boolean validateFields(String name, String description) throws InvalidParameterException {
		Boolean isOk = true;
		result = "";

		try {

			if (StringUtils.isBlank(name) && StringUtils.isBlank(description)) {

				result += "There are no fields to be edited. ";
				isOk = false;

			} else {

				if (!StringUtils.isBlank(name)) {
					if (name.length() > 50) {
						result += "The 'name' field has more than 50 characters. ";
						isOk = false;
					} else {
						Pattern specialChars = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
						Matcher hasSpecialChars = specialChars.matcher(name);

						if (hasSpecialChars.find()) {
							result += "The 'name' field doesn't accept special characters. ";
							isOk = false;
						}
					}
				}

				if (!StringUtils.isBlank(description)) {
					if (description.length() > 500) {
						result += "The 'description' field has more than 500 characters. ";
						isOk = false;
					}
				}
			}

			if (!isOk) {
				result = "The following error occurred: " + result;
				throw new InvalidParameterException(result);
			}

		} catch (InvalidParameterException ex) {
			System.out.println(ex);
			return false;
		}
		return isOk;
	}
	
	@Override
	@Transactional
	public FindCategoryResponse findCategories(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec){
		
		/*
			CONDITIONS FOR THE QUERIES:
			
			-If searchField and searchValue are not specified, must return every record
			 (With the exception of the limits per page and order type)
			 
		*/
		totalRecordsFound = 0;
		Integer lowerLimit, upperLimit = null;
		FindCategoryResponse findCategoryResponse = new FindCategoryResponse();
		List<Categories> categoriesFound = new ArrayList<Categories>();
		
		lowerLimit = ((pageNo*numberRec)-numberRec)+1;
		upperLimit = pageNo*numberRec;
		
		//If there is no WHERE sentence, it will return every record
		// with the exception of the limits per page they can have
		System.out.println("######SERVICE LAYER#######");
		if(StringUtils.isBlank(searchField) || searchField.trim().equals("") || searchValue.trim().equals("")){
			totalRecordsFound = categoryRepository.count();
			switch(orderBy){
				case "ID":
					if(orderType.equals("ASC")){
						categoriesFound = categoryRepository.findOrderByIdASC((long) lowerLimit, (long) upperLimit);
					}
					else{
						categoriesFound = categoryRepository.findOrderByIdDESC((long) lowerLimit, (long) upperLimit);
					}
				break;
				
				case "NAME":
					if(orderType.equals("ASC")){
						categoriesFound = categoryRepository.findOrderByNameASC((long) lowerLimit, (long) upperLimit);
					}
					else{
						categoriesFound = categoryRepository.findOrderByNameDESC((long) lowerLimit, (long) upperLimit);
					}
				break;
			}
		}
		else{
			//System.out.println("Search Field is not blank");
			switch(searchField.trim()){
			
				case "ID":
					categoriesFound = categoryRepository.findById((long) Integer.valueOf(searchValue));
					if(categoriesFound.size() > 0){
						totalRecordsFound = 1; //It will always be one, because a search by Id returns 1 result.
					}
				break;
				
				case "NAME":
					totalRecordsFound = categoryRepository.countByName(searchValue);
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							categoriesFound = categoryRepository.findByNameOrderByIdASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							categoriesFound = categoryRepository.findByNameOrderByIdDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
					
					case "NAME":
						if(orderType.equals("ASC")){
							categoriesFound = categoryRepository.findByNameOrderByNameASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							categoriesFound = categoryRepository.findByNameOrderByNameDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
				}
				break;
			}
		}
		
		findCategoryResponse.setCategories(categoriesFound);
		findCategoryResponse.setTotalRecords((int)this.totalRecordsFound);
		
		return findCategoryResponse;
		
	}
	
	public boolean validateSearchFields(String searchField, String searchValue, String orderBy, String orderType,
			String pageNo, String numberRec) throws InvalidParameterException {
		
		/*Conditions for searchField (If specified):
		- The searchField must be a valid column (ID, NAME)
	
	  Conditions for searchValue (If Specified):
	  	- For ID:
	  		+ Must be numeric and higher than 0
	  	- For NAME:
	  		+ Must contain characters (not empty)
	  		
	  Conditions for orderBy (If specified):
	  	- The orderBy must be a valid column (ID, NAME)
	  	 
	  Conditions for orderType (If specified):
	  	-The orderType must be "ASC" or "DESC"
	  	
	  	*/
		
		Boolean isOk = true;
		result ="";
		try {
			if (!StringUtils.isBlank(searchField)){
				if (!searchField.equals("ID") && !searchField.equals("NAME") 
						&& !searchField.trim().equals("")) {
					result += "The 'searchField' entered is not a valid column. ";
					isOk = false;
				} else {
					if (StringUtils.isBlank(searchValue)) {
						result += "The value entered for the column " + searchField + " is empty. Getting all results. ";
					} else {
						switch (searchField) {

						case "ID":
							boolean isIdNumeric = true;
							try {
								Integer.parseInt(searchValue);
							} catch (TypeMismatchException e) {
								isIdNumeric = false;
							} catch (NumberFormatException e) {
								isIdNumeric = false;
							}
							if (!isIdNumeric) {
								result += "The ID value entered is not a number. ";
								isOk = false;
							}
							else{
								if(Integer.valueOf(searchValue) <= 0){
									result += "The ID value must be higher than 0. ";
									isOk = false;
								}
							}
						break;

						// For 'name' field, is already validated
						// (That field must not be empty, if field is
						// specified)
						}
					}
				}
			}

			if (orderBy != null) {
				if (!orderBy.equals("ID") && !orderBy.equals("NAME")) {
					result += "The 'orderBy' field is not a valid column. ";
					isOk = false;
				}
			}

			if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
				result += "The 'orderType' field is invalid (must be asc or desc). ";
				isOk = false;
			}

			boolean isPageNoNumeric = true;
			try {
				Integer.parseInt(pageNo);
			} catch (TypeMismatchException e) {
				isPageNoNumeric = false;
			} catch (NumberFormatException e) {
				isPageNoNumeric = false;
			}

			boolean isNumberRecNumeric = true;
			try {
				Integer.parseInt(numberRec);
			} catch (TypeMismatchException e) {
				isNumberRecNumeric = false;
			} catch (NumberFormatException e) {
				isNumberRecNumeric = false;
			}

			if (!isPageNoNumeric) {
				result += "The 'pageNo' field is not numeric. ";
				isOk = false;
			}
			else {
				if (Integer.valueOf(pageNo) <= 0) {
					result += "The 'pageNo' must be greater than 0. ";
					isOk = false;
				}
			}

			if (!isNumberRecNumeric) {
				result += "The 'numberRec' field is not numeric. ";
				isOk = false;
			}
			else {
				if (Integer.valueOf(numberRec) <= 0) {
					result += "The 'numberRec' must be greater than 0. ";
					isOk = false;
				}
			}
			
			if(!isOk){
				result = "The following error(s) occurred: " + result;
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
