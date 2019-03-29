package com.usermanagement.model;

import java.util.List;

import lombok.Data;

@Data
public class FindUserResponse {

	private List<User> Users;
    private int TotalRecords;
    
}
