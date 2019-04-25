package com.usermanagement.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
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
	public FindCategoryResponse findCategories(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec){
		
		/*
			CONDITIONS FOR THE QUERIES:
			
			-If searchField and searchValue are not specified, must return every record
			 (With the exception of the limits per page and order type)
			 
		*/
		Integer lowerLimit, upperLimit = null;
		FindCategoryResponse findCategoryResponse = new FindCategoryResponse();
		List<Categories> categoriesFound = new ArrayList<Categories>();
		
		lowerLimit = ((pageNo*numberRec)-numberRec)+1;
		upperLimit = pageNo*numberRec;
		
		//If there is no WHERE sentence, it will return every record
		// with the exception of the limits per page they can have
		System.out.println("######SERVICE LAYER#######");
		if(StringUtils.isBlank(searchField) || searchField.trim().equals("") || searchValue.trim().equals("")){
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
				break;
				
				case "NAME":
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
		findCategoryResponse.setTotalRecords(categoriesFound.size());
		
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
						//isOk = false;
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