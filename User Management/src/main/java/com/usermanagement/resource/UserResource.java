package com.usermanagement.resource;

import java.util.List;

import org.hibernate.TypeMismatchException;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.model.FindUserResponse;
import com.usermanagement.model.User;
//import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserManager;

@RestController
// @RequestMapping("/user")
public class UserResource {

	@Autowired
	private UserManager userManager;

	String result;

	@GetMapping("/user/all")
	public List<User> getAll() {
		// usersRepository.flush();
		System.out.println(userManager.getUsers());
		return userManager.getUsers();
	}

	// delete user
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@ModelAttribute("User") User theUser) {
		// User user = userManager.findById(theUser.getId());
		if (userManager.deleteUser(theUser)) {
			return ResponseEntity.status(HttpStatus.OK).body("");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found. ");
		}
	}

	@RequestMapping(value = "/user/find", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> searchUsers(
			@RequestParam(value = "searchField", required = false) String searchField,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
			@RequestParam(value = "orderType", defaultValue = "asc", required = false) String orderType,
			@RequestParam(value = "pageNo", defaultValue = "1", required = false) String pageNo,
			@RequestParam(value = "numberRec", defaultValue = "10", required = false) String numberRec) {

		System.out.println("Search Field: "+searchField);
		System.out.println("Search Value :"+searchValue);
		System.out.println("Order Type: "+orderType);
		System.out.println("Order By: "+orderBy);
		System.out.println("Page Number: "+pageNo);
		System.out.println("Number records: "+numberRec);

		result = "";
		Boolean isOk = validateSearchFields((searchField == null)?"":searchField.toUpperCase(), orderBy.toUpperCase(), orderType.toUpperCase(),
				pageNo, numberRec);

		if (!isOk) {
			result = "The following error occurred: " + result;
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		} else {
			userManager.findUsers((searchField == null)?"":searchField.toUpperCase(), searchValue, orderBy.toUpperCase(),
					orderType.toUpperCase(), Integer.valueOf(pageNo), Integer.valueOf(numberRec));
			return ResponseEntity.status(HttpStatus.OK).body("");
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	// Previous parameter was HttpServletRequest request
	public ResponseEntity<?> createUser(@ModelAttribute("User") User theUser) {

		// String id = request.getParameter("id");
		result = "";
		Boolean isOk = validateFields(theUser);

		if (!isOk) {
			result = "The following error occurred: " + result;
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		} else {
			userManager.createUpdateUser(theUser);
			return ResponseEntity.status(HttpStatus.OK).body("");
		}
	}

	public Boolean validateSearchFields(String searchField, String orderBy, String orderType, String pageNo,
			String numberRec) {

		Boolean isOk = true;
		try {
			if (searchField != null) {
				if (!searchField.equals("ID") && !searchField.equals("FIRST_NAME") && !searchField.equals("LAST_NAME")
						&& !searchField.equals("EMAIL") && !searchField.equals("TYPE")) {
					result += "The 'searchField' field is not a valid column. ";
					isOk = false;
				}
			}
			
			if (orderBy != null) {
				if (!orderBy.equals("ID") && !orderBy.equals("FIRST_NAME") && !orderBy.equals("LAST_NAME")
						&& !orderBy.equals("EMAIL") && !orderBy.equals("TYPE")) {
					result += "The 'orderBy' field is not a valid column. ";
					isOk = false;
				}
			}

			if (!orderType.equals("ASC") && !orderType.equals("DESC")) {
				result += "The 'orderType' field is invalid (must be asc or desc). ";
				isOk = false;
			}

			boolean isPageNoNumeric = true;
			try {
				Integer.parseInt(pageNo);
			} catch (TypeMismatchException e) {
				isPageNoNumeric = false;
			} catch (NumberFormatException e) {
				isPageNoNumeric = false;
			}
			
			boolean isNumberRecNumeric = true;
			try {
				Integer.parseInt(numberRec);
			} catch (TypeMismatchException e) {
				isNumberRecNumeric = false;
			} catch (NumberFormatException e) {
				isNumberRecNumeric = false;
			}

			if (!isPageNoNumeric) {
				result += "The 'pageNo' field is not numeric. ";
				isOk = false;
			}
			
			if(!isNumberRecNumeric){
				result +="The 'numberRec' field is not numeric. ";
				isOk = false;
			}
			
			

		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return isOk;
	}

	public Boolean validateFields(User theUser) {

		Boolean isOk = true;

		if (theUser == null) {
			result = "Invalid User type. ";
			isOk = false;
		} else {
			String first_name = theUser.getFirst_name();
			String last_name = theUser.getLast_name();
			String email = theUser.getEmail();
			String type = String.valueOf(theUser.getType());

			try {
				if (first_name == null) {
					result += "The 'first_name' field is missing. ";
					isOk = false;
				} else {
					if (first_name.isEmpty()) {
						result += "The 'first_name' field is empty. ";
						isOk = false;
					}
				}

				if (last_name == null) {
					result += "The 'last_name' field is missing. ";
					isOk = false;
				} else {
					if (last_name.isEmpty()) {
						result += "The 'last_name' field is empty. ";
						isOk = false;
					}
				}

				if (email == null) {
					result += "The 'email' field is missing. ";
					isOk = false;
				} else {
					if (email.isEmpty()) {
						result += "The 'email' field is empty. ";
						isOk = false;
					} else {
						if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
							result += "The email entered is invalid. ";
							isOk = false;
						}
					}
				}

				if (type == null) {
					result += "The 'type' field is missing. ";
					isOk = false;
				} else {
					if (type.isEmpty()) {
						result += "The 'type' field is empty. ";
						isOk = false;
					} else {
						boolean isNumeric = true;
						try {

							Integer.parseInt(type);

						} catch (TypeMismatchException e) {
							isNumeric = false;
						} catch (NumberFormatException e) {
							isNumeric = false;
						}

						if (!isNumeric) {
							result += "The 'type' field is not numeric. ";
							isOk = false;
						} else {
							if (Integer.valueOf(type) < 0 || Integer.valueOf(type) > 2) {
								result += "The 'type' field must be between 0-2. ";
								isOk = false;
							}
						}
					}
				}

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		return isOk;
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@ModelAttribute("User") User theUser) {

		result = "";
		User user = userManager.findById(theUser.getId());
		Boolean isOk = validateFields(theUser);

		if (!isOk) {
			result = "The following error occurred: " + result;
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		} else {
			user.setFirst_name(theUser.getFirst_name());
			user.setLast_name(theUser.getLast_name());
			user.setEmail(theUser.getEmail());
			user.setType(theUser.getType());
			userManager.createUpdateUser(user);
			result = "Update success";
			return ResponseEntity.status(HttpStatus.OK).body("");
		}
	}

}
