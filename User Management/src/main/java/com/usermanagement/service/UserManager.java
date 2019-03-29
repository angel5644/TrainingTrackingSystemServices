package com.usermanagement.service;

import java.util.List;

import com.usermanagement.model.FindUserResponse;
import com.usermanagement.model.User;

public interface UserManager {
	public List <User> getUsers();
	public void createUpdateUser(User theUser);
	public User findById(int id);
	public boolean deleteUser(User theUser);
	public List<FindUserResponse> findUsers(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec);
}


