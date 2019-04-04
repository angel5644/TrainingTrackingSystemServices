package com.usermanagement.service;

import java.util.List;
import com.usermanagement.model.FindUserResponse;
import com.usermanagement.model.Users;

public interface UserManager {
		
	public List <Users> getUsers();
	public void createUpdateUser(Users theUser);
	public Users findById(Integer id);
	public List<Users> findByFirstName(String first_name);
	public List<Users> findByLastName(String last_name);
	public List<Users> findByEmail(String email);
	public List<Users> findByType(Integer type);
	public boolean deleteUser(Users theUser);
	public List<Users> viewUser(String id);
	public List<Users> getLastUserInserted();
	
	public FindUserResponse findUsers(String searchField,String searchValue,String orderBy,String orderType,Integer pageNo,Integer numberRec);
	public boolean validateSearchFields(String searchField, String searchValue, String orderBy, String orderType,
			String pageNo, String numberRec);
	public Boolean validateFields(Users theUser);
	public String getResult();
}


