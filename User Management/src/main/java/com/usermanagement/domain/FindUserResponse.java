package com.usermanagement.model;

import java.util.List;

public class FindUserResponse {

	private List<User> Users;
    private int TotalRecords;
    
	public List<User> getUsers() {
		return Users;
	}
	public void setUsers(List<User> users) {
		Users = users;
	}
	public int getTotalRecords() {
		return TotalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		TotalRecords = totalRecords;
	}
    
    
}
