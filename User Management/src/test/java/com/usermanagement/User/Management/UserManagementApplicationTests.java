package com.usermanagement.User.Management;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.usermanagement.model.User;
import com.usermanagement.resource.UserResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementApplicationTests {
	@Autowired
	
	@Mock
	UserResource userResource;
	
   @Test
    public void testUserInsert()
    { 
		String first_name = "Rodrigo";
		String last_name = "Velasco";
		String email = "rodrigo.velasco@4thsource.com";
		int type = 0;
		
		User user = new User();
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setEmail(email);
		user.setType(type);
		
		assertEquals("Insert success", userResource.createUser(user));      
    }
}
