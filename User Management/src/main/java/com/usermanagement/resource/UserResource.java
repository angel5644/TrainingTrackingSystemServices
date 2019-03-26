package com.usermanagement.resource;

import java.util.List;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.model.User;
//import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserManager;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	private UserManager userManager;
	
	String result;
	
	@GetMapping("/all")
    public List<User> getAll() {
        //usersRepository.flush();
        System.out.println(userManager.getUsers());
        return userManager.getUsers();
    }
	
	@RequestMapping(value= "/create_user_post", method = RequestMethod.POST)
	@ResponseBody
	//Previous parameter was HttpServletRequest request
	public String createUser(@ModelAttribute("User") User theUser){
		
		//String id = request.getParameter("id");
		result="";
		Boolean isOk = validateFields(theUser);
	    
	    if(!isOk){
        	result = "The following error occurred "+ result;
        }
        else{
        	userManager.createUpdateUser(theUser);
	        result = "Insert success";
        }
	    
	    return result;
	}
	
	public Boolean validateFields(User theUser){
		
		String first_name= theUser.getFirst_name();
		String last_name= theUser.getLast_name();
		String email= theUser.getEmail();
		String type= String.valueOf(theUser.getType());
		
		Boolean isOk = true;
		
		try{
	        if(first_name.isEmpty() || first_name == null){
	        	result += "The 'first_name' filed is missing. ";
	        	isOk = false;
	        }
	        
	        if(last_name.isEmpty() || last_name == null){
	        	result += "The 'last_name' filed is missing. ";
	        	isOk = false;
	        }
	        
	        if(email.isEmpty() || email == null){
	        	result += "The 'email' filed is missing. ";
	        	isOk = false;
	        }
	        else{
	        	if(!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
	        		result += "The email entered is invalid. ";
	        		isOk = false;
	        	}
	        }
	        
	        if(type.isEmpty() || type == null){
	        	result += "The 'type' field is missing. ";
	        	isOk = false;
	        }
	        else{
	        	boolean isNumeric = true;
	            try {

	                Integer.parseInt(type);

	            }catch (NumberFormatException e) {
	                isNumeric = false;
	            }
	            
	            if(!isNumeric){
	            	result += "The 'type' field is not numeric. ";
	            	isOk = false;
	            }
	            else{
	            	if(Integer.valueOf(type) < 0 || Integer.valueOf(type) > 2){
	            		result += "The 'type' field must be between 0-2";
	            		isOk = false;
	            	}
	            }
	        }
	    }catch(Exception ex){
	        System.out.println(ex);
	    }
		return isOk;
	}
	
	@RequestMapping(value= "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") final Integer id,@ModelAttribute("User") User theUser){
		
		result="";
		User user = userManager.findById(id);
		Boolean isOk = validateFields(user);
		
		if(!isOk){
        	result = "The following error occurred "+ result;
        }
        else{
        	user.setFirst_name(theUser.getFirst_name());
        	user.setLast_name(theUser.getLast_name());
        	user.setEmail(theUser.getEmail());
        	user.setType(theUser.getType());
        	userManager.createUpdateUser(user);
	        result = "Update success";
        }
		
		return result;
	}
}
