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
import com.usermanagement.model.Categories;
import com.usermanagement.resource.CategoryResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateCategoryManagementApplicationTests {

	@Mock
	CategoryResource categoryResource;

	// Test UserInsert function
	@Test
	public void updateCategorySuccessfully() {
		String name = "A category title N";
		String description = "A category desription D";

		Categories category = new Categories();
		category.setName(name);
		category.setDescription(description);
		
		when(categoryResource.updateCategory(category.getId(),category.getName(),category.getDescription())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		
		assertEquals(HttpStatus.OK, categoryResource.updateCategory(category.getId(),category.getName(),category.getDescription()).getStatusCode());
	}

	@Test
	public void updateCategoryWhenNameAndDescriptionExceedsTheirSize() {
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

		when(categoryResource.updateCategory(category.getId(),category.getName(),category.getDescription())).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
		
		assertEquals(HttpStatus.BAD_REQUEST, categoryResource.updateCategory(category.getId(),category.getName(),category.getDescription()).getStatusCode());
	}
	
	@Test
	public void updateCategoryWhenNameIsAlreadyRegistered(){
		String name = "A category title 1";
		String description = "A category desription 1";

		Categories category = new Categories();
		category.setName(name);
		category.setDescription(description);
		System.out.println("Updating a duplicate category...");
		when(categoryResource.updateCategory(category.getId(),category.getName(),category.getDescription())).thenReturn(new ResponseEntity<>(HttpStatus.CONFLICT));
		
		assertEquals(HttpStatus.CONFLICT, categoryResource.updateCategory(category.getId(),category.getName(),category.getDescription()).getStatusCode());
	}
}
