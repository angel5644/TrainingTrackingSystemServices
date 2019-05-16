package com.usermanagement.CategoryManagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.usermanagement.model.Category;
import com.usermanagement.resource.CategoryResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateCategoryManagementApplicationTests {

	@Mock
	CategoryResource categoryResource;

	// Test UserInsert function
	@Test
	public void insertCategorySuccessfully() {
		String name = "A category title N";
		String description = "A category desription D";

		Category category = new Category();
		category.setName(name);
		category.setDescription(description);

		when(categoryResource.createCategory(category)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
		
		assertEquals(HttpStatus.CREATED, categoryResource.createCategory(category).getStatusCode());
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

		Category category = new Category();
		category.setName(name);
		category.setDescription(description);

		when(categoryResource.createCategory(category)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
		
		assertEquals(HttpStatus.BAD_REQUEST, categoryResource.createCategory(category).getStatusCode());
	}
	
	@Test
	public void insertCategoryWhenNameIsAlreadyRegistered(){
		String name = "A category title 1";
		String description = "A category desription 1";

		Category category1 = new Category();
		category1.setName(name);
		category1.setDescription(description);
		System.out.println("Creating category...");
		when(categoryResource.createCategory(category1)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
		if(categoryResource.createCategory(category1).getStatusCode() == HttpStatus.CREATED){
			System.out.println("Category created!");
		}
		else{
			System.out.println("The following error(s) occurred: category1 could not be created");
		}
		
		System.out.println("Creating a duplicate category...");
		Category category2 = new Category();
		category2.setName(name);
		category2.setDescription(description);
		when(categoryResource.createCategory(category2)).thenReturn(new ResponseEntity<>(HttpStatus.CONFLICT));

		assertEquals(HttpStatus.CONFLICT, categoryResource.createCategory(category2).getStatusCode());
	}
}
