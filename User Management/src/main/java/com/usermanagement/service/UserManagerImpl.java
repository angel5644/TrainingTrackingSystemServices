package com.usermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import com.usermanagement.model.FindUserResponse;
import com.usermanagement.model.Users;
import com.usermanagement.repository.UserRepository;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
    UserRepository userRepository;
	
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
}
