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
import com.usermanagement.model.Users;
import com.usermanagement.resource.UserResource;
import com.usermanagement.service.UserManagerImpl;

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

		Users user = new Users();
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

		Users user = new Users();
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);

		assertEquals(HttpStatus.BAD_REQUEST, userResource.createUser(user).getStatusCode());
	}

	// Test UserUpdate function
	@Test
	public void testUserUpdate() {
		String first_name = "Rodrigo";
		String last_name = "Velasco";
		String email = "rodrigo.velasco@4thsource.com";
		int type = 0;

		Users user = new Users();
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);

		userResource.createUser(user);
		
		UserManagerImpl userManager = new UserManagerImpl();
		Users userInserted = userManager.getLastUserInserted();

		first_name = "Luis";
		last_name = "Robles";
		email = "luis.robles@4thsource.com";
		type = 1;

		userInserted.setFirstName(first_name);
		userInserted.setLastName(last_name);
		userInserted.setEmail(email);
		userInserted.setType(type);

		assertEquals(HttpStatus.OK, userResource.update(userInserted).getStatusCode());
		
		userResource.deleteUser(userInserted);
	}

	// Test UserUpdate function, sending empty or null fields
	@Test
	public void testUserUpdateWithEmptyFields() {
		String first_name = null;
		String last_name = "";
		String email = "";
		int type = 0;

		Users user = new Users();

		user.setId(1);
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);

		assertEquals(HttpStatus.BAD_REQUEST, userResource.deleteUser(user).getStatusCode());
	}
	
	@Test
	public void testdeleteUser_When_Delete_Its_OK(){
		
		String first_name = "Rodrigo";
		String last_name = "Velasco";
		String email = "rodrigo.velasco@4thsource.com";
		int type = 0;
		
		Users user = new Users();
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);
		
		userResource.createUser(user);
		
		//UserManagerImpl obj = new UserManagerImpl();
		Users theUser = new Users();
		theUser.setId(1);
		theUser.setFirstName(first_name);
		theUser.setLastName(last_name);
		theUser.setEmail(email);
		theUser.setType(type);
		
		//boolean deleteUser = obj.deleteUser(theUser);
		assertEquals(HttpStatus.OK, userResource.deleteUser(user).getStatusCode());	
	}
	
	@Test
	public void testdeleteUser_When_Delete_Its_BAD_REQUEST(){
		
		String first_name = "Rodrigo";
		String last_name = "Velasco";
		String email = "rodrigo.velasco@4thsource.com";
		int type = 0;
		
		Users user = new Users();
		user.setId(1);
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmail(email);
		user.setType(type);
		
		userResource.createUser(user);
		
		Users theUser = new Users();
		theUser.setId(2);
		theUser.setFirstName(first_name);
		theUser.setLastName(last_name);
		theUser.setEmail(email);
		theUser.setType(type);

		assertEquals(HttpStatus.BAD_REQUEST, userResource.deleteUser(user).getStatusCode());	
	}
	
	@Test
	public void testviewUser_When_View_Its_OK(){
		assertEquals(HttpStatus.OK, userResource.viewUser("1").getStatusCode());
	}
	
	@Test
	public void testviewUser_When_View_Its_BAD_REQUEST(){
		//assertEquals(HttpStatus.BAD_REQUEST, userResource.viewUser(user).getStatusCode());
	}
	
	@Test
	public void sendGET() throws IOException {
		
		/*URL obj = new URL("http://localhost:9091/user/find?orderBy=&orderType&searchValue=Luis Alberto&numberRec&pageNo&searchField=first_name");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}
		*/
		
		HttpURLConnection con = null;

        try {
        	URL url = new URL("https://localhost:9091/user/find?orderBy=&orderType&searchValue=Luis Alberto&numberRec&pageNo&searchField=first_name");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
    		int responseCode = con.getResponseCode();
    		System.out.println("GET Response Code :: " + responseCode);
    		if (responseCode == HttpURLConnection.HTTP_OK) { // success
    			BufferedReader in = new BufferedReader(new InputStreamReader(
    					con.getInputStream()));
    			String inputLine;
    			StringBuffer response = new StringBuffer();

    			while ((inputLine = in.readLine()) != null) {
    				response.append(inputLine);
    			}
    			in.close();

    			// print result
    			System.out.println(response.toString());
    		} else {
    			System.out.println("GET request not worked");
    		}
        } catch (MalformedURLException e) {
            System.out.println("########################## Internal error: "+ e);
        } catch (IOException e) {
        	System.out.println("########################## Internal error: "+ e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
	}
	
	@Test
	public void findUsersByFirstNameOrderByLastNameASC(){
		String searchField = "first_name";
		String searchValue = "Luis Alberto";
		String orderBy = "last_name";
		String orderType ="asc";
		Integer pageNo = 1;
		Integer numberRec = 2;
		
		UserManagerImpl userManager = new UserManagerImpl();
		boolean isNull = userManager.findUsers(searchField, searchValue, orderBy, orderType, pageNo, numberRec) == null;
		
		assertEquals(false, isNull);
	}
	
	@Test
	public void findUsersWithDefaultValuesAndEmptySearchField(){
		String searchField = "";
		String searchValue = "";
		String orderBy = "id";
		String orderType ="asc";
		Integer pageNo = 1;
		Integer numberRec = 10;
		
		UserManagerImpl userManager = new UserManagerImpl();
		boolean isNull = userManager.findUsers(searchField, searchValue, orderBy, orderType, pageNo, numberRec) == null;
		
		assertEquals(false, isNull);
	}
	
	
	@Test
	public void findUsersByTypeOrderByEmailDesc(){
		String searchField = "type";
		String searchValue = "1";
		String orderBy = "email";
		String orderType ="desc";
		Integer pageNo = 1;
		Integer numberRec = 5;
		
		UserManagerImpl userManager = new UserManagerImpl();
		boolean isNull = userManager.findUsers(searchField, searchValue, orderBy, orderType, pageNo, numberRec) == null;
		
		assertEquals(false, isNull);
	}

}
