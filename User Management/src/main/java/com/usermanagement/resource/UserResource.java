package com.usermanagement.resource;

import java.util.List;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		String first_name= theUser.getFirst_name();
		String last_name= theUser.getLast_name();
		String email= theUser.getEmail();
		String type= String.valueOf(theUser.getType());
		
		String result="";
		Boolean isOk = true;

	    try{
	        User u = new User();
	        
	        if(first_name.isEmpty() || first_name == null){
	        	result += "The 'first_name' filed is missing. ";
	        	isOk = false;
	        }
	        else{
	        	u.setFirst_name(first_name);
	        }
	        
	        if(last_name.isEmpty() || last_name == null){
	        	result += "The 'last_name' filed is missing. ";
	        	isOk = false;
	        }
	        else{
	        	u.setLast_name(last_name);
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
	        	else{
	        		u.setEmail(email);
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
	            	}
	            	else{
	            		u.setType(Integer.parseInt(type));
	            	}
	            }
	        }
	        
	        if(!isOk){
	        	result = "The following error occurred "+ result;
	        }
	        else{
	        	userManager.createUser(u);
		        result = "Insert success";
	        }
	        
	    }catch(Exception ex){
	        System.out.println(ex);
	    }
	    return result;
	}
}
