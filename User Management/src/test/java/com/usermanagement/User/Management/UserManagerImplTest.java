package com.usermanagement.User.Management;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.usermanagement.service.UserManagerImpl;

public class UserManagerImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	/*
	@Test
	
	public void test_validateSearchField_Should_ReturnFalse_When_serachFieldIsInvalid() {
		//arrange
		UserManagerImpl obj = new UserManagerImpl();
	
		String searchField = "any_string_different_known_valid_fields";
		String searchValue = "";
		String orderBy = "";
		String orderType = "";
		int pageNo = 1;
		int numberRec = 10;
		boolean expected = false;
		
		 // act
		boolean result = obj.validateSearchFields(searchField, searchValue, orderBy, orderType, pageNo, numberRec);
		
		// assert
		assertEquals(result, expected);
		}



		@Test
		public void test_validateSearchFields_Should_ReturnTrue_When_SearchFieldIsValid() {
		// arrange 
		UserManagerImpl obj = new UserManagerImpl();
		String searchField = "first_name"; //first_name
		String searchValue = "";
		String orderBy = "";
		String orderType = "";
		int pageNo = 1;
		int numberRec = 10;
		boolean expected = true;
		
	// act
		boolean result = obj.validateSearchFields(searchField, searchValue, orderBy, orderType, pageNo, numberRec);
		
		// assert
		assertEquals(expected, result);
		}
		@Test
		public void test_validateSearchValue_Should_ReturnTrue_When_SearchFieldIsFirstNameAndSearchValueIsAnyString() {
		// arrange 
		UserManagerImpl obj = new UserManagerImpl();
		String searchField = "first_name";
		String searchValue = "any_string_different_known_valid_fields";
		String orderBy = "";
		 String orderType = "";
		int pageNo = 1;
		int numberRec = 10;
		boolean expected = true;
		
		// act
		boolean result = obj.validateSearchFields(searchField, searchValue, orderBy, orderType, pageNo, numberRec);
		
	// assert
		assertEquals(result, expected);
		}

@Test
		public void test_validateSearchValue_Should_ReturnFalse_When_SearchFieldIsTypeAndSearchValueIsValid() {
	// arrange 
		 UserManagerImpl obj = new UserManagerImpl();
		 String searchField = "Type";
		 String searchValue = "2";
		 String orderBy = "";
		 String orderType = "";
		int pageNo = 1;
		int numberRec = 10;
		 boolean expected = false;
		
		// act
		boolean result = obj.validateSearchFields(searchField, searchValue, orderBy, orderType, pageNo, numberRec);
		
		// assert
		assertEquals(expected, result);
		}
		@Test
	public void test_validateorderby_Should_ReturnFalse_When_orderByIsInvalid() {
		// arrange 
		UserManagerImpl obj = new UserManagerImpl();
		String searchField = "";
		String searchValue = "";
		String orderBy = "any_string_different_known_valid_fields";
		String orderType = "";
		int pageNo = 1;
		int numberRec = 10;
		boolean expected = false;
		
		// act
		 boolean result = obj.validateSearchFields(searchField, searchValue, orderBy, orderType, pageNo, numberRec);
		
		// assert
		 assertEquals(result, expected);
		 }


		@Test
		public void test_validateordeBy_Should_ReturnTrue_When_orderByIsValid() {
		// arrange 
		 UserManagerImpl obj = new UserManagerImpl();
		String searchField = ""; //first_name
		String searchValue = "";
		String orderBy = "first_name";
		String orderType = "";
		int pageNo = 1;
		int numberRec = 10;
		boolean expected = true;
		
		// act
		boolean result = obj.validateSearchFields(searchField, searchValue, orderBy, orderType, pageNo, numberRec);
		
		// assert
		assertEquals(expected, result);
		}
		@Test
		public void test_validateorderType_Should_ReturnFalse_When_orderTypeIsInvalid() {
		
			// arrange 
			UserManagerImpl obj = new UserManagerImpl();
			String searchField = "";
			String searchValue = "";
			String orderBy = "";
			String orderType = "asva";
			int pageNo = 1;
			int numberRec = 10;
			boolean expected = false;
				
			// act
			boolean result = obj.validateSearchFields(searchField, searchValue, orderBy, orderType, pageNo, numberRec);
			
			// assert
			assertEquals(result, expected);
			}
@Test
		public void test_validateorderType_Should_ReturnTrue_When_SearchOrderTypeIsValid() {
			// arrange 
			UserManagerImpl obj = new UserManagerImpl();
			 String searchField = ""; //first_name
			 String searchValue = "";
			 String orderBy = "";
			 String orderType = "ASC";
			 int pageNo = 1;
			 int numberRec = 10;
			 boolean expected = true;
			
			 // act
			 boolean result = obj.validateSearchFields(searchField, searchValue, orderBy, orderType, pageNo, numberRec);
		
			 // assert
			 assertEquals(expected, result);
			 }
		
			
*/
		}


