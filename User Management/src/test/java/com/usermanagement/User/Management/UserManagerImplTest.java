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

	@Test
	
	public void test_validateSearchField_Should_ReturnFalse_When_serachFieldIsInvalid() {
		//arrange
		UserManagerImpl obj = new UserManagerImpl();
	
		String searchField = "any_string_different_known_valid_fields";
		String searchValue = "";
		String orderBy = "";
		String orderType = "";
		String pageNo = "1";
		String numberRec = "10";
		boolean expected = false;
		
		 // act
		boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
				(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
				numberRec);
		
		// assert
		assertEquals(result, expected);
		}



		@Test
		public void test_validateSearchField_Should_ReturnTrue_When_SearchFieldIsValid() {
		// arrange 
		UserManagerImpl obj = new UserManagerImpl();
		String searchField = "first_name"; //first_name
		String searchValue = "";
		String orderBy = "id";
		String orderType = "asc";
		String pageNo = "1";
		String numberRec = "10";
		boolean expected = true;
		
	// act
		boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
				(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
				numberRec);
		
		// assert
		assertEquals(expected, result);
		}
		@Test
		public void test_validateSearchField_Should_ReturnTrue_When_SearchFieldIsFirstNameAndSearchValueIsAnyString() {
		// arrange 
		UserManagerImpl obj = new UserManagerImpl();
		String searchField = "first_name";
		String searchValue = "any_string_different_known_valid_fields";
		String orderBy = "id";
		String orderType = "asc";
		String pageNo = "1";
		String numberRec = "10";
		boolean expected = true;
		
		// act
		boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
				(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
				numberRec);
		
	// assert
		assertEquals(result, expected);
		}

@Test
		public void test_validateSearchField_Should_ReturnTrue_When_SearchFieldIsTypeAndSearchValueIsValid() {
	// arrange 
		 UserManagerImpl obj = new UserManagerImpl();
		 String searchField = "Type";
		 String searchValue = "2";
		 String orderBy = "id";
		 String orderType = "asc";
		 String pageNo = "1";
		 String numberRec = "10";
		 boolean expected = true;
		
		// act
		boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
				(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
				numberRec);
		
		// assert
		assertEquals(expected, result);
		}
		@Test
	public void test_validateSearchField_Should_ReturnFalse_When_orderByIsInvalid() {
		// arrange 
		UserManagerImpl obj = new UserManagerImpl();
		String searchField = "";
		String searchValue = "";
		String orderBy = "any_string_different_known_valid_fields";
		String orderType = "dfgdfg";
		String pageNo = "1";
		String numberRec = "10";
		boolean expected = false;
		
		// act
		 boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
					(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
							numberRec);
		
		// assert
		 assertEquals(result, expected);
		 }


		@Test
		public void test_validateSearchField_Should_ReturnTrue_When_orderByIsValid() {
		// arrange 
		 UserManagerImpl obj = new UserManagerImpl();
		String searchField = ""; //first_name
		String searchValue = "";
		String orderBy = "first_name";
		String orderType = "asc";
		String pageNo = "1";
		String numberRec = "10";
		boolean expected = true;
		
		// act
		boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
				(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
				numberRec);
		
		// assert
		assertEquals(expected, result);
		}
		@Test
		public void test_validateSearchField_Should_ReturnFalse_When_orderTypeIsInvalid() {
		
			// arrange 
			UserManagerImpl obj = new UserManagerImpl();
			String searchField = "";
			String searchValue = "";
			String orderBy = "id";
			String orderType = "any_string_different_known_valid_fields";
			String pageNo = "1";
			String numberRec = "10";
			boolean expected = false;
				
			// act
			boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
					(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
							numberRec);
			
			// assert
			assertEquals(result, expected);
			}
@Test
		public void test_validateSearchField_Should_ReturnTrue_When_orderTypeIsValid() {
			// arrange 
			UserManagerImpl obj = new UserManagerImpl();
			 String searchField = ""; //first_name
			 String searchValue = "";
			 String orderBy = "id";
			 String orderType = "ASC";
			 String pageNo = "1";
			 String numberRec = "10";
			 boolean expected = true;
			
			 // act
			 boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
						(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
								numberRec);
		
			 // assert
			 assertEquals(expected, result);
			 }
		
			
@Test
public void test_validateSearchField_Should_ReturnFalse_When_pageNoIsInvalid() {

	// arrange 
	UserManagerImpl obj = new UserManagerImpl();
	String searchField = "";
	String searchValue = "";
	String orderBy = "id";
	String orderType = "ASC";
	String pageNo = "any_string_different_known_valid_fields";
	String numberRec = "10";
	boolean expected = false;
		
	// act
	boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
			(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
					numberRec);
	
	// assert
	assertEquals(result, expected);
	}
@Test
public void test_validateSearchField_Should_ReturnTrue_When_pageNoIsValid() {
	// arrange 
	UserManagerImpl obj = new UserManagerImpl();
	 String searchField = ""; //first_name
	 String searchValue = "";
	 String orderBy = "id";
	 String orderType = "ASC";
	 String pageNo = "1";
	 String numberRec = "10";
	 boolean expected = true;
	
	 // act
	 boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
				(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
						numberRec);

	 // assert
	 assertEquals(expected, result);
	 }

@Test
public void test_validateSearchField_Should_ReturnFalse_When_numberRecIsInvalid() {

	// arrange 
	UserManagerImpl obj = new UserManagerImpl();
	String searchField = "";
	String searchValue = "";
	String orderBy = "id";
	String orderType = "ASC";
	String pageNo = "1";
	String numberRec = "any_string_different_known_valid_fields";
	boolean expected = false;
		
	// act
	boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
			(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
					numberRec);
	
	// assert
	assertEquals(result, expected);
	}
@Test
public void test_validateSearchField_Should_ReturnTrue_When_numberRecIsValid() {
	// arrange 
	UserManagerImpl obj = new UserManagerImpl();
	 String searchField = ""; //first_name
	 String searchValue = "";
	 String orderBy = "id";
	 String orderType = "ASC";
	 String pageNo = "1";
	 String numberRec = "10";
	 boolean expected = true;
	
	 // act
	 boolean result = obj.validateSearchFields((searchField == null) ? "" : searchField.toUpperCase(),
				(searchValue == null) ? "" : searchValue, orderBy.toUpperCase(), orderType.toUpperCase(), pageNo,
						numberRec);

	 // assert
	 assertEquals(expected, result);
	 }

	

		}




