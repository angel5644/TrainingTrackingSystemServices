package com.usermanagement.service;

import java.util.List;

import com.usermanagement.model.User;

public interface UserManager {
	public List <User> getUsers();
	public void createUpdateUser(User theUser);
	public User findById(int id);
	public boolean deleteUser(User theUser);
}


