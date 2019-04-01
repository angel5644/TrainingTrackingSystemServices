package com.usermanagement.User.Management;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.usermanagement.model.User;
import com.usermanagement.resource.UserResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementApplicationTests {
	@Autowired

	@Mock
	UserResource userResource;

	// Test UserInsert function
	@Test
	public void testUserInsert() {
		String first_name = "Rodrigo";
		String last_name = "Velasco";
		String email = "rodrigo.velasco@4thsource.com";
		int type = 0;

		User user = new User();
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);

		assertEquals(HttpStatus.OK, userResource.createUser(user).getStatusCode());
	}

	// Test UserInsert function, sending empty or null fields
	@Test
	public void testUserInsertWithEmptyFields() {
		String first_name = null;
		String last_name = "";
		String email = "";
		int type = 0;

		User user = new User();
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);

		assertEquals(HttpStatus.BAD_REQUEST, userResource.createUser(user).getStatusCode());
	}

	// Test UserUpdate function
	@Test
	public void testUserUpdate() {
		int id = 1;
		String first_name = "Rodrigo";
		String last_name = "Velasco";
		String email = "rodrigo.velasco@4thsource.com";
		int type = 0;

		User user = new User();
		user.setId(id);
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);

		userResource.createUser(user);

		first_name = "Luis";
		last_name = "Robles";
		email = "luis.robles@4thsource.com";
		type = 1;

		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);

		assertEquals(HttpStatus.OK, userResource.update(user).getStatusCode());
	}

	// Test UserUpdate function, sending empty or null fields
	@Test
	public void testUserUpdateWithEmptyFields() {
		String first_name = null;
		String last_name = "";
		String email = "";
		int type = 0;

		User user = new User();

		user.setId(1);
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);

		assertEquals(HttpStatus.BAD_REQUEST, userResource.update(user).getStatusCode());
	}

	@Test
	public void testSearchUsers() {
		
		
		
		StringBuilder result = new StringBuilder();
		
		try {
			URL url = new URL("http://localhost:9091/user/find?searchField&searchValue&orderBy&orderType&pageNo=1&numberRec=10");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result.toString());
	}

}
