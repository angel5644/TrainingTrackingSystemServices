package com.usermanagement.CategoryManagement;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.usermanagement.resource.CategoryResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListCategoryManagementApplicationTests {

	@Autowired
	
	@Mock
	CategoryResource categoryResource;

	@Test
	public void listCategories() {
		assertEquals(HttpStatus.OK, categoryResource.listCategories("","","ID","ASC","1","10").getStatusCode());
	}

	@Test
	public void searchCategoriesByAKnownColumn(){
		assertEquals(HttpStatus.OK, categoryResource.listCategories("ID","44","ID","ASC","1","10").getStatusCode());
	}
	
	@Test
	public void searchCategoriesByAnUnknownColumn(){
		assertEquals(HttpStatus.BAD_REQUEST, categoryResource.listCategories("ID2","44","ID","ASC","1","10").getStatusCode());
	}
	
	@Test
	public void searchCategoriesWhenIdNotFound(){
		assertEquals(HttpStatus.OK, categoryResource.listCategories("ID","1","ID","ASC","1","10").getStatusCode());
	}
	
	@Test
	public void searchCategoriesWhenNameNotFound(){
		assertEquals(HttpStatus.OK, categoryResource.listCategories("NAME","NAME NOT RECORDED","ID","ASC","1","10").getStatusCode());
	}
	
	@Test
	public void searchCategoriesWhenPageNoIsInvalid(){
		assertEquals(HttpStatus.BAD_REQUEST, categoryResource.listCategories("","","ID","ASC","-10","10").getStatusCode());
	}
	
	@Test
	public void searchCategoriesWhenIdIsInvalid(){
		assertEquals(HttpStatus.BAD_REQUEST, categoryResource.listCategories("ID","-5","ID","ASC","1","10").getStatusCode());
	}
}