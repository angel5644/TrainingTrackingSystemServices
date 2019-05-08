package com.usermanagement.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.usermanagement.model.Courses;
import com.usermanagement.model.CourseRequest;
import com.usermanagement.repository.CourseRepository;

@Service
public class CourseManagerImpl implements CourseManager {
	
	@Autowired
    CourseRepository courseRepository;
	
	private String result;
	private List<Courses> courses;
	
	public String getResult(){
		return this.result;
	}
	
	public List<Courses> getCourses(){
		return this.courses;
	}
	
	@Override
	@Transactional
	public Boolean createCourse(CourseRequest theCourse) throws DuplicateKeyException{
		result = "";
		Boolean isOk = true;
		try{
			if(courseRepository.findByName(theCourse.getName()).size() > 0){
				result = "There is already a course with that name. ";
				isOk = false;
			}
			else{
				courses.clear();
				// For loop to created each course with its own category
				// Content field should be encrypted with base64 encode
				for(int i = 0; i < theCourse.getCategories().length; i++){
					Courses tempCourse = new Courses();
					tempCourse.setName(theCourse.getName());
					tempCourse.setDescription(theCourse.getDescription());
					tempCourse.setCategories(theCourse.getCategories()[i]);
					tempCourse.setContent(Base64.getEncoder().encodeToString(theCourse.getContent().getBytes(StandardCharsets.UTF_8)));
					courses.add(tempCourse);
					courseRepository.save(tempCourse);
				}
			}
			
			if(!isOk){
				result = "The following error(s) occurred: " + result;
				throw new DuplicateKeyException(result); 
			}
		}
		catch(DuplicateKeyException ex){
			System.out.println(ex);
			return false;
		}
		return isOk;
	}
	
	public Boolean validateFields(CourseRequest theCourse) throws InvalidParameterException{
		Boolean isOk = true;
		result = "";

		try {
			if (theCourse == null) {
				result = "Invalid Course type. ";
				return false;
				
			} else {
				//Missing validations for Courses (Currently applies for categories)
				String name = theCourse.getName();
				String description = theCourse.getDescription();
				
				if (StringUtils.isBlank(name)) {
					result += "The 'name' field is empty or missing. ";
					isOk = false;
				}
				else{
					if(name.length() > 50){
						result += "The 'name' field has more than 50 characters. ";
						isOk = false;
					}
					else{
						Pattern specialChars = Pattern.compile("^[A-Za-z0-9 ]+$");
						Matcher hasNotSpecialChars = specialChars.matcher(name);

						if (!hasNotSpecialChars.find()) {
							result += "The 'name' field doesn't accept special characters. ";
							isOk = false;
						}
					}
				}
				
				if (StringUtils.isBlank(description)) {
					result += "The 'description' field is empty or missing. ";
					isOk = false;
				}
				else{
					if(description.length() > 500){
						result += "The 'description' field has more than 500 characters. ";
						isOk = false;
					}
				}	
			}
			
			if(!isOk){
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
