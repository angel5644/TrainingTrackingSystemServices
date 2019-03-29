package com.usermanagement.service;

import java.util.List;

import com.usermanagement.domain.FindUserResponse;
import com.usermanagement.domain.LoginRequest;
import com.usermanagement.model.User;

public interface UserManager {
	
	FindUserResponse getUserInfo(LoginRequest request);
	
//	List<User> getUsers(*/);
//	void createUpdateUser(User theUser);
//	User findById(int id);
//	boolean deleteUser(UserInfo theUser);
//	List<FindUserResponse> findUsers(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec);
}


