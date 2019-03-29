package com.usermanagement.domain;

import lombok.Data;

@Data
public class LoginRequest {
		String searchField;
		String searchValue;
		String orderType;
		String orderBy;
		String pageNo;
		String numberRec;
}
