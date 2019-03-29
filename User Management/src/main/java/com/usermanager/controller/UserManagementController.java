package com.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.usermanagement.domain.FindUserResponse;
import com.usermanagement.domain.LoginRequest;
import com.usermanagement.service.UserManager;

@Controller
public class UserManagementController {
	@Autowired
	UserManager serviceInt;
	
	@GetMapping(value="/user")
	@ResponseStatus(HttpStatus.CREATED)
	public FindUserResponse loginget( //Outside
			@RequestParam(value = "searchField") String searchField,
			@RequestParam(value = "searchValue") String searchValue,
			@RequestParam(value = "orderType") String orderType,//asc,dec
			@RequestParam(value = "orderBy") String orderBy,//firstName
			@RequestParam(value = "pageNo") String pageNo,//1
			@RequestParam(value = "numberRec") String numberRec) throws Exception {//10
	
	LoginRequest request = new LoginRequest();//Object.

	request.setSearchField(searchField);
	request.setSearchValue(searchValue);
	request.setOrderType(orderType);
	request.setOrderBy(orderBy);
	request.setPageNo(pageNo);
	request.setNumberRec(numberRec);
	
	//validateRequest(searchField,searchValue, orderType, orderBy, pageNo, numberRec);
	
//	FindUserResponse response = serviceInt.getUsers(searchField,searchValue, 
//			orderType, orderBy, pageNo, numberRec);
	return new FindUserResponse(serviceInt.getUserInfo(request));
	}
}
