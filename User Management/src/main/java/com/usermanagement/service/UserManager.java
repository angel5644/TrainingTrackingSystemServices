package com.usermanagement.service;

import java.util.List;

import com.usermanagement.model.User;

public interface UserManager {

	public List <User> getUsers();
	public void createUser(User theUser);
}
