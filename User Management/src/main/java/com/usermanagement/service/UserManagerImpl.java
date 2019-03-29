package com.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.model.FindUserResponse;
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
	public User findById(int id){
		return userRepository.findById(id).get();
	}
	
	@Override
	@Transactional
	public List<User> findByFirstName(String first_name){
		return userRepository.findByFirstName(first_name);
		//return null;
	}
	
	@Override
	@Transactional
	public List<User> findByLastName(String last_name){
		return userRepository.findByLastName(last_name);
		//return null;
	}
	
	@Override
	@Transactional
	public List<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	@Override
	@Transactional
	public List<User> findByType(Integer type){
		return userRepository.findByType(type);
	}
	
	@Override
	@Transactional
	public void createUpdateUser(User theUser){
		userRepository.save(theUser);
	}
	
	@Override
    @Transactional
    public boolean deleteUser(User theUser) {
		userRepository.deleteById(theUser.getId());
		return false;
    }
	
	@Override
	@Transactional
	public FindUserResponse findUsers(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec){
		
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
		FindUserResponse findUserResponse = new FindUserResponse();
		List<User> usersFound = userRepository.findByFirstName(searchValue);
		//List<User> usersFound = null;
		findUserResponse.setUsers(usersFound);
		findUserResponse.setTotalRecords(usersFound.size());
		
		
		return findUserResponse;
	}
}
