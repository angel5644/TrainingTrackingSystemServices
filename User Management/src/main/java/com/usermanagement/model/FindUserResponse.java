package com.usermanagement.model;

import java.util.List;
import lombok.Data;

@Data
public class FindUserResponse {

	private List<UserInfo> Users;
    private int TotalRecords;
    
    @Data
    public class UserInfo {
    	private Integer id;
    	private String firstName;
    	private String lastName;
    	private String email;
    	private Integer type;
    }
}


