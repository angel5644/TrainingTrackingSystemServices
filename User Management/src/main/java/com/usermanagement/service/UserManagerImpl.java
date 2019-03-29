package com.usermanagement.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.domain.FindUserResponse;
import com.usermanagement.domain.LoginRequest;
import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
    UserRepository userRepository;
	
	@Override
	public FindUserResponse getUserInfo(LoginRequest request) {
		// TODO Auto-generated method stub
		/****
		 * 
		 * VALIDACIONES
		 * 
		 */
		
		if(StringUtils.equalsIgnoreCase(request.getSearchField(), "firstName"){
			
		}
		return null;
	
	}
	
	@Override
	@Transactional
	public List <User> getUsers(){
		return userRepository.findAll();
	}
	
	@Override
	@Transactional
	public User findById(int id){
		return userRepository.findById(id).get();
	}
	
	@Override
	@Transactional
	public void createUpdateUser(User theUser){
		userRepository.save(theUser);
	}
	
	@Override
    @Transactional
    public boolean deleteUser(User theUser) {
		//Here you have to validate if theUser received is NULL or not
		//I want to find the user by id if it's null this method will return false it's not null it will delete the user and return true
		if()
		userRepository.deleteById(theUser.getId());
    }
	
	@Override
	@Transactional
	public List<FindUserResponse> findUsers(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec){
		return null;
	}

}
