package com.usermanagement.resource;

import java.util.List;

import org.hibernate.TypeMismatchException;
//import org.hibernate.TypeMismatchException;
//import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.usermanagement.model.Users;
//import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserManager;

@RestController
// @RequestMapping("/user")
public class UserResource{

	@Autowired
	private UserManager userManager;

	String result;

	@GetMapping("/user/all")
	public List<Users> getAll() {
		// usersRepository.flush();
		System.out.println(userManager.getUsers());
		return userManager.getUsers();
	}

	//Delete User
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@ModelAttribute("User") Users theUser) {
		// User user = userManager.findById(theUser.getId());
		if (userManager.deleteUser(theUser)) {
			return ResponseEntity.status(HttpStatus.OK).body("");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found. ");
		}
	}
	//View User
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> viewUser(@PathVariable("id") final String id) {
		
		boolean isTypeNumeric = true;
		try {
			Integer.parseInt(id);
		} catch (TypeMismatchException e) {
			isTypeNumeric = false;
		} catch (NumberFormatException e) {
			isTypeNumeric = false;
		}

		if (!isTypeNumeric) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The following error(s) occurred: The id entered is not a valid number. ");
		} else {
			if (userManager.findById(Integer.valueOf(id)) != null) {
				return ResponseEntity.status(HttpStatus.OK).body(userManager.viewUser(id));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
			}
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> searchUsers(@RequestParam(value = "searchField", required = false) String searchField,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
			@RequestParam(value = "orderType", defaultValue = "asc", required = false) String orderType,
			@RequestParam(value = "pageNo", defaultValue = "1", required = false) String pageNo,
			@RequestParam(value = "numberRec", defaultValue = "10", required = false) String numberRec) {

		System.out.println("######CONTROLLER LAYER#######");
		System.out.println("Search Field: " + searchField);
		System.out.println("Search Value :" + searchValue);
		System.out.println("Order Type: " + orderType);
		System.out.println("Order By: " + orderBy);
		System.out.println("Page Number: " + pageNo);
		System.out.println("Number records: " + numberRec);
		System.out.println("#############################");

		boolean isOk = userManager.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
				(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
				numberRec);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userManager.getResult());
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(userManager.findUsers((searchField == null) ? "" : searchField.toUpperCase(),
							(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(),
							Integer.valueOf(pageNo), Integer.valueOf(numberRec)));
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	// Previous parameter was HttpServletRequest request
	//public ResponseEntity<?> createUser(@ModelAttribute("CreateUserRequest") CreateUserRequest theUser) {
	public ResponseEntity<?> createUser(@ModelAttribute("Users") Users theUser) {

		// String id = request.getParameter("id");
		result = "";
		Boolean isOk = userManager.validateFields(theUser);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userManager.getResult());
		} else {
			userManager.createUpdateUser(theUser);
			return ResponseEntity.status(HttpStatus.OK).body(theUser);
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@ModelAttribute("User") Users theUser) {

		result = "";
		Users user = userManager.findById(theUser.getId());
		Boolean isOk = userManager.validateFields(theUser);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userManager.getResult());
		} else {
			user.setFirstName(theUser.getFirstName());
			user.setLastName(theUser.getLastName());
			user.setEmail(theUser.getEmail());
			user.setType(theUser.getType());
			userManager.createUpdateUser(user);
			result = "Update success";
			return ResponseEntity.status(HttpStatus.OK).body("");
		}
	}

}
