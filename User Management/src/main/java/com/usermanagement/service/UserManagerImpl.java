package com.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
    UserRepository userRepository;
	
	@Override
	@Transactional
	public List <User> getUsers(){
		return userRepository.findAll();
	}
	
	@Override
	@Transactional
	public void createUser(User theUser){
		userRepository.save(theUser);
	}
}
