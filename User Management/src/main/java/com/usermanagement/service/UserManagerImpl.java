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
	public Users findById(int id){
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
		
		if(StringUtils.isBlank(searchField)){
			switch(orderBy){
				case "ID":
					if(orderType.equals("ASC")){
						
					}
					else{
						
					}
				break;
				
				case "FIRST_NAME":
					if(orderType.equals("ASC")){
						
					}
					else{
						
					}
				break;
					
				case "LAST_NAME":
					if(orderType.equals("ASC")){
						
					}
					else{
						
					}
				break;
					
				case "EMAIL":
					if(orderType.equals("ASC")){
						
					}
					else{
						
					}
				break;
					
				case "TYPE":
					if(orderType.equals("ASC")){
						
					}
					else{
						
					}
				break;
			}
		}
		else{
			switch(searchField){
			
				case "ID":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							usersFound = userRepository.findByIdOrderByIdASC(searchValue,lowerLimit,upperLimit);
						}
						else{
							
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
				}
				break;
				
				case "FIRST_NAME":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
				}
				break;
					
				case "LAST_NAME":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
				}
				break;
					
				case "EMAIL":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
				}
				break;
					
				case "TYPE":
					switch(orderBy){
					case "ID":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
					
					case "FIRST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "LAST_NAME":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "EMAIL":
						if(orderType.equals("ASC")){
							
						}
						else{
							
						}
					break;
						
					case "TYPE":
						if(orderType.equals("ASC")){
							
						}
						else{
							
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
