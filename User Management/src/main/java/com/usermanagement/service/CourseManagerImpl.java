package com.usermanagement.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.usermanagement.model.Courses;
import com.usermanagement.model.CatCourses;
import com.usermanagement.model.CourseRequest;
import com.usermanagement.model.CourseResponse;
import com.usermanagement.repository.CatCourseRepository;
import com.usermanagement.repository.CategoryRepository;
import com.usermanagement.repository.CourseRepository;

@Service
public class CourseManagerImpl implements CourseManager {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CatCourseRepository catCourseRepository;
	
	@Autowired
    CategoryRepository categoryRepository;

	private String result;
	private CourseResponse courseResponse;

	public String getResult() {
		return this.result;
	}

	public CourseResponse getCourseResponse() {
		return this.courseResponse;
	}

	@Override
	@Transactional
	public Boolean createCourse(CourseRequest theCourse) throws DuplicateKeyException {
		result = "";
		Boolean isOk = true;
		Boolean isRowNotRepeated = true;
		try {
			if (courseRepository.findByName(theCourse.getName()).size() > 0) {
				result += "There is already a course with that name. ";
				isOk = false;
				isRowNotRepeated = false;
			} else {
				
				//This will check if the categories inserted exists in the Categorie's Table
				Boolean categoryNumbersExist = true;
				
				for(int i = 0; i < theCourse.getCategories().length; i++){
					if(!categoryRepository.findById(theCourse.getCategories()[i]).isPresent()){
						result += "The category with index '"+theCourse.getCategories()[i]+"' doesn't exist in the database. ";
						categoryNumbersExist = false;
						isOk = false;
					}
				}
				
				if(categoryNumbersExist){
					courseResponse = new CourseResponse();
					
					// Content field should be encrypted with base64 encode
					Courses tempCourse = new Courses();
					tempCourse.setName(theCourse.getName());
					tempCourse.setDescription(theCourse.getDescription());
					//tempCourse.setCategories(theCourse.getCategories()[i]);
					tempCourse.setContent(Base64.getEncoder()
							.encodeToString(theCourse.getContent().getBytes(StandardCharsets.UTF_8)));
					courseRepository.save(tempCourse);
					
					courseResponse.setId(tempCourse.getId());
					courseResponse.setName(tempCourse.getName());
					courseResponse.setDescription(tempCourse.getDescription());
					courseResponse.setCategories(theCourse.getCategories());
					courseResponse.setContent(tempCourse.getContent());
					
					// For loop to created each record with a course/category relation
					for (int i = 0; i < theCourse.getCategories().length; i++) {
						
						CatCourses tempCatCourse = new CatCourses();
						
						tempCatCourse.setIdCategory(theCourse.getCategories()[i]);
						tempCatCourse.setIdCourse(tempCourse.getId());
						
						catCourseRepository.save(tempCatCourse);
					}
				}
			}

			if (!isOk) {
				if(!isRowNotRepeated){
					throw new DuplicateKeyException(result);
				}
				else{
					throw new NoSuchElementException(result);
				}
			}
		} catch (DuplicateKeyException ex) {
			System.out.println(ex);
			return false;
		} catch (NoSuchElementException ex) {
			System.out.println(ex);
			return false;
		}
		return isOk;
	}

	public Boolean validateFields(CourseRequest theCourse) throws InvalidParameterException {
		Boolean isOk = true;
		result = "";

		try {
			if (theCourse == null) {
				result = "Invalid Course type. ";
				return false;

			} else {
				// Missing validations for Courses (Currently applies for
				// categories)
				String name = theCourse.getName();
				String description = theCourse.getDescription();

				if (StringUtils.isBlank(name)) {
					result += "The 'name' field is empty or missing. ";
					isOk = false;
				} else {
					if (name.length() > 50) {
						result += "The 'name' field has more than 50 characters. ";
						isOk = false;
					} else {
						Pattern specialChars = Pattern.compile("^[A-Za-z0-9 ]+$");
						Matcher hasNotSpecialChars = specialChars.matcher(name);

						if (!hasNotSpecialChars.find()) {
							result += "The 'name' field doesn't accept special characters. ";
							isOk = false;
						}
					}
				}

				if (!StringUtils.isBlank(description) && description.length() > 500) {
					result += "The 'description' field has more than 500 characters. ";
					isOk = false;
				}
				
				//This checks if the categories parameter is an array
				if(theCourse.getCategories() !=null && theCourse.getCategories().length > 0 && theCourse.getCategories().getClass().isArray()){
					
					//This checks if the categories parameter contains numbers
					for(int i=0; i < theCourse.getCategories().length; i++){
						String category = String.valueOf(theCourse.getCategories()[i]);
						boolean isCategoryNumeric = true;
						try {
							Integer.parseInt(category);
						} catch (TypeMismatchException e) {
							isCategoryNumeric = false;
						} catch (NumberFormatException e) {
							isCategoryNumeric = false;
						}
						
						if(!isCategoryNumeric){
							result += "The 'categories' field must contain numbers. ";
							isOk = false;
							break;
						}
					}
					
				}
				else{
					result += "The 'categories' field must contain at least one category index. ";
					isOk = false;
				}
				
				if (!StringUtils.isBlank(description)) {
					
					if(description.length() > 4000){
						result += "The 'content' field must not be longer than 4000 characters. ";
						isOk = false;
					}
					
				}
				else{
					result += "The 'content' field must not be empty. ";
					isOk = false;
				}
			}

			if (!isOk) {
				result = "The following error occurred: " + result;
				throw new InvalidParameterException(result);
			}

		} catch (InvalidParameterException ex) {
			System.out.println(ex);
			return false;
		}
		return isOk;
	}

}
