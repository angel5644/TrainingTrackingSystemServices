package com.usermanagement.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.TypeMismatchException;

import com.usermanagement.model.FindUserResponse;
import com.usermanagement.model.Users;
import com.usermanagement.repository.UserRepository;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
    UserRepository userRepository;
	
	private String result;
	
	@Override
	@Transactional
	public List <Users> getUsers(){
		return userRepository.findAll();
	}
	
	@Override
	@Transactional
	public Users findById(Integer id){
		return userRepository.findById(id).get();
	}
	
	@Override
	@Transactional
	public List<Users> findByFirstName(String first_name){
		return userRepository.findByFirstName(first_name);
		//return null;
	}
	
	@Override
	@Transactional
	public List<Users> findByLastName(String last_name){
		return userRepository.findByLastName(last_name);
		//return null;
	}
	
	@Override
	@Transactional
	public List<Users> findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	@Override
	@Transactional
	public List<Users> findByType(Integer type){
		return userRepository.findByType(type);
	}
	
	@Override
	@Transactional
	public void createUpdateUser(Users theUser){
		userRepository.save(theUser);
	}
	
	@Override
    @Transactional
    public boolean deleteUser(Users theUser) {
		if(findById(theUser.getId()) == null){
			return false;
		}
		else{
			userRepository.deleteById(theUser.getId());
			return true;
		}
    }
	
	@Override
	@Transactional
	public List<Users> viewUser(String id){
		return (userRepository.findById(Integer.valueOf(id)) == null)? null: userRepository.findById((long)Integer.valueOf(id));
	}
	
	@Override
	@Transactional
	public FindUserResponse findUsers(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec){
		
		/*
			CONDITIONS FOR THE QUERIES:
			
			-If searchField and searchValue are not specified, must return every record
			 (With the exception of the limits per page and order type)
			 
		*/
		Integer lowerLimit, upperLimit = null;
		FindUserResponse findUserResponse = new FindUserResponse();
		List<Users> usersFound = new ArrayList<Users>();
		
		lowerLimit = ((pageNo*numberRec)-numberRec)+1;
		upperLimit = pageNo*numberRec;
		
		//If there is no WHERE sentence, it will return every record
		// with the exception of the limits per page they can have
		System.out.println("######SERVICE LAYER#######");
		if(StringUtils.isBlank(searchField)){
			switch(orderBy){
				case "ID":
					if(orderType.equals("ASC")){
						usersFound = userRepository.findOrderByIdASC((long) lowerLimit, (long) upperLimit);
					}
					else{
						usersFound = userRepository.findOrderByIdDESC((long) lowerLimit, (long) upperLimit);
					}
				break;
				
				case "FIRST_NAME":
					if(orderType.equals("ASC")){
						usersFound = userRepository.findOrderByFirstNameASC((long) lowerLimit, (long) upperLimit);
					}
					else{
						usersFound = userRepository.findOrderByFirstNameDESC((long) lowerLimit, (long) upperLimit);
					}
				break;
					
				case "LAST_NAME":
					if(orderType.equals("ASC")){
						usersFound = userRepository.findOrderByLastNameASC((long) lowerLimit, (long) upperLimit);
					}
					else{
						usersFound = userRepository.findOrderByLastNameDESC((long) lowerLimit, (long) upperLimit);
					}
				break;
					
				case "EMAIL":
					if(orderType.equals("ASC")){
						usersFound = userRepository.findOrderByEmailASC((long) lowerLimit, (long) upperLimit);
					}
					else{
						usersFound = userRepository.findOrderByEmailDESC((long) lowerLimit, (long) upperLimit);
					}
				break;
					
				case "TYPE":
					if(orderType.equals("ASC")){
						usersFound = userRepository.findOrderByTypeASC((long) lowerLimit, (long) upperLimit);
					}
					else{
						usersFound = userRepository.findOrderByTypeDESC((long) lowerLimit, (long) upperLimit);
					}
				break;
			}
		}
		else{
			//System.out.println("Search Field is not blank");
			switch(searchField){
				case "ID":
					usersFound = userRepository.findById((long) Integer.valueOf(searchValue));
				break;
				
				case "FIRST_NAME":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByFirstNameOrderByIdASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByFirstNameOrderByIdDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByFirstNameOrderByFirstNameASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByFirstNameOrderByFirstNameDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByFirstNameOrderByLastNameASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByFirstNameOrderByLastNameDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByFirstNameOrderByEmailASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByFirstNameOrderByEmailDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByFirstNameOrderByTypeASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByFirstNameOrderByTypeDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
				}
				break;
					
				case "LAST_NAME":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByLastNameOrderByIdASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByLastNameOrderByIdDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByLastNameOrderByFirstNameASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByLastNameOrderByFirstNameDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByLastNameOrderByLastNameASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByLastNameOrderByLastNameDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByLastNameOrderByEmailASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByLastNameOrderByEmailDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByLastNameOrderByTypeASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByLastNameOrderByTypeDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
				}
				break;
					
				case "EMAIL":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByEmailOrderByIdASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByEmailOrderByIdDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByEmailOrderByFirstNameASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByEmailOrderByFirstNameDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByEmailOrderByLastNameASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByEmailOrderByLastNameDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByEmailOrderByEmailASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByEmailOrderByEmailDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByEmailOrderByTypeASC(searchValue, lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByEmailOrderByTypeDESC(searchValue, lowerLimit, upperLimit);
						}
					break;
				}
				break;
					
				case "TYPE":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByTypeOrderByIdASC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByTypeOrderByIdDESC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByTypeOrderByFirstNameASC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByTypeOrderByFirstNameDESC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByTypeOrderByLastNameASC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByTypeOrderByLastNameDESC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByTypeOrderByEmailASC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByTypeOrderByEmailDESC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByTypeOrderByTypeASC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
						else{
							usersFound = userRepository.findByTypeOrderByTypeDESC(Integer.valueOf(searchValue), lowerLimit, upperLimit);
						}
					break;
				}
				break;
			}
		}
		
		findUserResponse.setUsers(usersFound);
		findUserResponse.setTotalRecords(usersFound.size());
		
		/*Integer lowerLimit, upperLimit = null;
		FindUserResponse findUserResponse = new FindUserResponse();
		
		lowerLimit = ((pageNo*numberRec)-numberRec)+1;
		upperLimit = pageNo*numberRec;
		
		if(searchField.equals("") || searchField.isEmpty()){
			List<User> usersFound = userRepository.findUsersWithoutField(lowerLimit, upperLimit);
			findUserResponse.setUsers(usersFound);
			findUserResponse.setTotalRecords(usersFound.size());
		}
		else{
			List<User> usersFound = userRepository.findUsersWithField(searchValue,lowerLimit, upperLimit);
			findUserResponse.setUsers(usersFound);
			findUserResponse.setTotalRecords(usersFound.size());
		}
		*/
		//FindUserResponse findUserResponse = new FindUserResponse();
		//List<User> usersFound = userRepository.findByFirstName(searchValue);
		//List<User> usersFound = null;
		//findUserResponse.setUsers(usersFound);
		//findUserResponse.setTotalRecords(usersFound.size());
		
		return findUserResponse;
		
	}
	
	public boolean validateSearchFields(String searchField, String searchValue, String orderBy, String orderType,
			String pageNo, String numberRec) throws InvalidParameterException {

		/*Conditions for searchField (If specified):
		- The searchField must be a valid column (ID, FIRST_NAME,
		  LAST_NAME, EMAIL, TYPE)
	
	  Conditions for searchValue (If Specified):
	  	- For ID:
	  		+ Must be numeric and higher than 0
	  	- For First_name and Last_Name:
	  		+ Must contain characters (not empty)
	  	- For Email:
	  		+ Must contain characters (not empty)
	  		+ Must be a valid email address
	  	- For Type:
	  		+ Must be numeric and between 0 and 2.
	  		
	  Conditions for orderBy (If specified):
	  	- The orderBy must be a valid column (ID, FIRST_NAME,
		  LAST_NAME, EMAIL, TYPE)
	  	 
	  Conditions for orderType (If specified):
	  	-The orderType must be "ASC" or "DESC"
	  	
	  	*/
		
		Boolean isOk = true;
		result ="";
		try {
			if (!StringUtils.isBlank(searchField)){
				if (!searchField.equals("ID") && !searchField.equals("FIRST_NAME") && !searchField.equals("LAST_NAME")
						&& !searchField.equals("EMAIL") && !searchField.equals("TYPE")) {
					result += "The 'searchField' entered is not a valid column. ";
					isOk = false;
				} else {
					if (StringUtils.isBlank(searchValue)) {
						result += "The value entered for the column " + searchField + " is empty. ";
						isOk = false;
					} else {
						switch (searchField) {

						/*case "ID":
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
						break;*/

						// For first name and last name are already validated
						// (Those fields must not be empty, if field is
						// specified)

						case "EMAIL":
							if (!searchValue.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
								result += "The email value is invalid. ";
								isOk = false;
							}
						break;

						case "TYPE":
							boolean isTypeNumeric = true;
							try {
								Integer.parseInt(searchValue);
							} catch (TypeMismatchException e) {
								isTypeNumeric = false;
							} catch (NumberFormatException e) {
								isTypeNumeric = false;
							}

							if (!isTypeNumeric) {
								result += "The type value is not numeric. ";
								isOk = false;
							} else {
								if (Integer.valueOf(searchValue) < 0 || Integer.valueOf(searchValue) > 2) {
									result += "The type value must be between 0-2. ";
									isOk = false;
								}
							}
						break;
						}
					}
				}
			}

			if (orderBy != null) {
				if (!orderBy.equals("ID") && !orderBy.equals("FIRST_NAME") && !orderBy.equals("LAST_NAME")
						&& !orderBy.equals("EMAIL") && !orderBy.equals("TYPE")) {
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

			if (!isNumberRecNumeric) {
				result += "The 'numberRec' field is not numeric. ";
				isOk = false;
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
