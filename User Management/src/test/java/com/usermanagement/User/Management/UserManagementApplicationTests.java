package com.usermanagement.User.Management;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import javax.transaction.Transactional;

import org.aspectj.lang.annotation.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.usermanagement.UserManagementApplication;
import com.usermanagement.resource.UserResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementApplicationTests {
	
   @Test
   @Transactional
    public void testUserInsert()
    { 
		UserResource userResource = null;
		String first_name = "Rodrigo";
		String last_name = "Velasco";
		String email = "rodrigo.velasco@4thsource.com";
		int type = 0;
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("http://localhost:9091/user/create_user_post");
		request.setMethod("POST");
		request.setContentType("text/plain");
		request.setCharacterEncoding("UTF-8");
		request.setParameter("first_name", first_name);
		request.setParameter("last_name", last_name);
		request.setParameter("email", email);
		request.setParameter("type", String.valueOf(type));
		
		try
		{
			System.out.println("ABERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR" + request.getContentAsString());
		}
		catch (IllegalStateException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Resultado = " + userResource.createUser(request));
		//assertEquals("Insert success", userResource.createUser(request));      
    }
}
