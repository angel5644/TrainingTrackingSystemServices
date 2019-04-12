package com.usermanagement.CategoryManagement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.usermanagement.model.Categories;
import com.usermanagement.resource.CategoryResource;
import com.usermanagement.service.CategoryManagerImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateCategoryManagementApplicationTests {

	@Autowired

	@Mock
	CategoryResource categoryResource;
	@Mock
	CategoryManagerImpl categoryManager;

	// Test UserInsert function
	@Test
	public void insertCategorySuccessfully() {
		//Delete part is missing
		String name = "A category title";
		String description = "A category desription";

		Categories category = new Categories();
		category.setName(name);
		category.setDescription(description);

		assertEquals(HttpStatus.OK, categoryResource.createCategory(category).getStatusCode());
	}

	@Test
	public void insertCategoryWhenNameAndDescriptionExceedsTheirSize() {
		String name = "This is a category title name with more than 50 characters.";
		String description = "This is a category description with more than 500 characters"
							+"Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
							+"sed eiusmod tempor incidunt ut labore et dolore magna aliqua."
							+"Ut enim ad minim veniam, quis nostrud exercitation ullamco"
							+"laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure"
							+"reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla"
							+"pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in"
							+"culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum"
							+"dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor.";

		Categories category = new Categories();
		category.setName(name);
		category.setDescription(description);

		assertEquals(HttpStatus.BAD_REQUEST, categoryResource.createCategory(category).getStatusCode());
	}
	
	@Test
	public void insertCategoryWhenNameIsAlreadyRegistered(){
		//Missing parts where have to create another category and then delete it
		String name = "A category title";
		String description = "A category desription";

		Categories category = new Categories();
		category.setName(name);
		category.setDescription(description);

		assertEquals(HttpStatus.CONFLICT, categoryResource.createCategory(category).getStatusCode());
	}
}
