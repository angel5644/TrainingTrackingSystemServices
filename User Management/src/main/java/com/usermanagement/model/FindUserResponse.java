package com.usermanagement.model;

import java.util.List;
import lombok.Data;

@Data
public class FindUserResponse {

	private int totalRecords;
	private List<Users> users;
    
}


