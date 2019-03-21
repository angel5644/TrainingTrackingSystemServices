package com.usermanagement.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
    UserRepository userRepository;
	
	@RequestMapping(value= "/create_user_post", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(HttpServletRequest request){
		
		//String id = request.getParameter("id");
		String first_name= request.getParameter("first_name");
		String last_name= request.getParameter("last_name");
		String email= request.getParameter("email");
		String type= request.getParameter("type");
		
		String result="";

	    try{
	        User u = new User();
	        u.setFirst_name(first_name);
	        u.setLast_name(last_name);
	        u.setEmail(email);
	        u.setType(Integer.parseInt(type));
	        
	        userRepository.save(u);
	        result = "Insert success";
	    }catch(Exception ex){
	        System.out.println(ex);
	    }
	    return result;
	}
}
