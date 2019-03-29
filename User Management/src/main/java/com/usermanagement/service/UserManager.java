package com.usermanagement.service;

import java.util.List;

import com.usermanagement.model.FindUserResponse;
import com.usermanagement.model.User;

public interface UserManager {
	public List <User> getUsers();
	public void createUpdateUser(User theUser);
	public User findById(int id);
	public List<User> findByFirstName(String first_name);
	public List<User> findByLastName(String last_name);
	public List<User> findByEmail(String email);
	public List<User> findByType(Integer type);
	public boolean deleteUser(User theUser);
	public FindUserResponse findUsers(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec);
}


