package com.usermanagement.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.List;
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
import com.usermanagement.model.Course;
import com.usermanagement.model.CourseListResponse;
import com.usermanagement.model.CatCourse;
import com.usermanagement.model.CourseRequest;
import com.usermanagement.model.CourseResponse;
import com.usermanagement.model.Users;
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

	public String getResult() {
		return this.result;
	}

	@Override
	@Transactional
	public boolean deleteCourse(int courseId, Users theUser) {

		// If the User is not an admin will return a null response
		if (theUser.getType() != 2) {
			return false;
		} else {
			courseRepository.deleteById(courseId);
			return true;
		}
	}

	@Override
	@Transactional
	public CourseListResponse getCourses(Users theUser) {

		// If the User is not a trainer nor admin will return a null response
		if (theUser.getType() == 0) {
			return null;
		} else {
			// Gets all the courses in the database
			List<Course> courses = courseRepository.findAll();
			// Creates a new course response
			CourseResponse[] courseResponse = new CourseResponse[courses.size()];

			// Creates the collection for the collection categories inside the
			// course response
			for (int i = 0; i < courses.size(); i++) {
				courseResponse[i] = new CourseResponse();
				courseResponse[i].setId(courses.get(i).getId());
				courseResponse[i].setName(courses.get(i).getName());

				List<CatCourse> categoriesByCourseId = catCourseRepository
						.searchCategoriesByIdCourse(courses.get(i).getId());

				int[] tempCategories = new int[categoriesByCourseId.size()];
				for (int j = 0; j < categoriesByCourseId.size(); j++) {
					tempCategories[j] = categoriesByCourseId.get(j).getIdCategory();
				}

				// Sets the category collection that each course has
				courseResponse[i].setCategories(tempCategories);
			}

			// Creates a new course list response to return
			CourseListResponse courseListResponse = new CourseListResponse();
			courseListResponse.setTotalRecords(courses.size());
			// Sets all the course elements found in the database
			courseListResponse.setCourseElements(courseResponse);

			return courseListResponse;
		}
	}

	@Override
	@Transactional
	public CourseResponse getCourse(int courseId) {

		// Checks if the course exists in the database
		if (!courseRepository.findById(courseId).isPresent()) {

			result += " The course ID entered doesn't exist in the database. ";
			return null;

		} else {
			// Gets the course selected by Id in the database
			Course course = courseRepository.findById(courseId).get();
			// Creates a new course response
			CourseResponse courseDetails = new CourseResponse();

			List<CatCourse> categoriesByCourseId = catCourseRepository.searchCategoriesByIdCourse(course.getId());

			int[] tempCategories = new int[categoriesByCourseId.size()];
			for (int i = 0; i < categoriesByCourseId.size(); i++) {
				tempCategories[i] = categoriesByCourseId.get(i).getIdCategory();
			}

			courseDetails.setId(course.getId());
			courseDetails.setName(course.getName());
			courseDetails.setDescription(course.getDescription());
			courseDetails.setCategories(tempCategories);
			courseDetails.setContent(new String(Base64.getDecoder().decode(course.getContent())));

			return courseDetails;
		}
	}

	@Override
	@Transactional
	public CourseResponse createCourse(CourseRequest theCourse) throws DuplicateKeyException {
		result = "";
		Boolean isOk = true;
		Boolean isRowNotRepeated = true;
		CourseResponse courseResponse = null;

		try {
			if (courseRepository.findByName(theCourse.getName()).size() > 0) {
				result += "There is already a course with that name. ";
				isOk = false;
				isRowNotRepeated = false;
			} else {

				// This will check if the categories inserted exists in the
				// Categorie's Table
				Boolean categoryNumbersExist = true;

				for (int i = 0; i < theCourse.getCategories().length; i++) {
					if (!categoryRepository.findById(theCourse.getCategories()[i]).isPresent()) {
						result += "The category with index '" + theCourse.getCategories()[i]
								+ "' doesn't exist in the database. ";
						categoryNumbersExist = false;
						isOk = false;
					}
				}

				if (categoryNumbersExist) {

					courseResponse = new CourseResponse();
					// Content field should be encrypted with base64 encode
					Course tempCourse = new Course();
					tempCourse.setName(theCourse.getName());
					tempCourse.setDescription(theCourse.getDescription());
					// tempCourse.setCategories(theCourse.getCategories()[i]);
					tempCourse.setContent(Base64.getEncoder()
							.encodeToString(theCourse.getContent().getBytes(StandardCharsets.UTF_8)));
					courseRepository.save(tempCourse);

					courseResponse.setId(tempCourse.getId());
					courseResponse.setName(tempCourse.getName());
					courseResponse.setDescription(tempCourse.getDescription());
					courseResponse.setCategories(theCourse.getCategories());
					courseResponse.setContent(new String(Base64.getDecoder().decode(tempCourse.getContent())));

					// For loop to created each record with a course/category
					// relation
					for (int i = 0; i < theCourse.getCategories().length; i++) {

						CatCourse tempCatCourse = new CatCourse();

						tempCatCourse.setIdCategory(theCourse.getCategories()[i]);
						tempCatCourse.setIdCourse(tempCourse.getId());

						catCourseRepository.save(tempCatCourse);
					}
				}
			}

			if (!isOk) {
				if (!isRowNotRepeated) {
					throw new DuplicateKeyException(result);
				} else {
					throw new NoSuchElementException(result);
				}
			}
		} catch (DuplicateKeyException ex) {
			System.out.println(ex);
			return null;
		} catch (NoSuchElementException ex) {
			System.out.println(ex);
			return null;
		}
		return courseResponse;
	}

	@Override
	@Transactional
	public CourseResponse updateCourse(int id, CourseRequest theCourse) throws DuplicateKeyException {
		result = "";
		Boolean isOk = true;
		Boolean isRowNotRepeated = true;
		CourseResponse courseResponse = null;

		try {

			if (id < 1) {
				result += " The 'id' parameter should be greater than 0.";
				throw new InvalidParameterException(result);
			} else {

				// Check if the course to be edited, exists in the database
				Course course = (courseRepository.findById(id).isPresent()) ? courseRepository.findById(id).get()
						: null;

				if (course == null) {
					result = " The course to be edited was not found in the database. ";
					isOk = false;
				} else {

					// Find if the new name for the course to be edited, already
					// exists in another record
					if (courseRepository.findDuplicate(theCourse.getName(), id).size() > 0) {
						result += "There is already a course with that name. ";
						isOk = false;
						isRowNotRepeated = false;
					} else {

						// This will delete existing categories related with the
						// course to be edited
						// Then it will be re-inserted in the table catcourses

						catCourseRepository.deleteCategoriesByCourse(id);

						// This will check if the categories inserted exists in
						// the
						// Categorie's Table
						Boolean categoryNumbersExist = true;

						for (int i = 0; i < theCourse.getCategories().length; i++) {
							if (!categoryRepository.findById(theCourse.getCategories()[i]).isPresent()) {
								result += "The category with index '" + theCourse.getCategories()[i]
										+ "' doesn't exist in the database. ";
								categoryNumbersExist = false;
								isOk = false;
							}
						}

						if (categoryNumbersExist) {
							courseResponse = new CourseResponse();

							// Content field should be encrypted with base64
							// encode
							course.setName(theCourse.getName());
							course.setDescription(theCourse.getDescription());
							// tempCourse.setCategories(theCourse.getCategories()[i]);
							course.setContent(Base64.getEncoder()
									.encodeToString(theCourse.getContent().getBytes(StandardCharsets.UTF_8)));
							courseRepository.save(course);

							courseResponse.setId(course.getId());
							courseResponse.setName(course.getName());
							courseResponse.setDescription(course.getDescription());
							courseResponse.setCategories(theCourse.getCategories());
							courseResponse.setContent(new String(Base64.getDecoder().decode(course.getContent())));

							// For loop to create each record with a
							// course/category relation
							for (int i = 0; i < theCourse.getCategories().length; i++) {

								CatCourse tempCatCourse = new CatCourse();

								tempCatCourse.setIdCategory(theCourse.getCategories()[i]);
								tempCatCourse.setIdCourse(course.getId());

								catCourseRepository.save(tempCatCourse);
							}
						}
					}
				}
			}

			if (!isOk) {
				if (!isRowNotRepeated) {
					throw new DuplicateKeyException(result);
				} else {
					throw new NoSuchElementException(result);
				}
			}
		} catch (DuplicateKeyException ex) {
			System.out.println(ex);
			return null;
		} catch (NoSuchElementException ex) {
			System.out.println(ex);
			return null;
		} catch (InvalidParameterException ex) {
			System.out.println(ex);
			return null;
		}
		return courseResponse;
	}

	public boolean validateFields(CourseRequest theCourse) throws InvalidParameterException {
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

				// This checks if the categories parameter is an array
				if (theCourse.getCategories() != null && theCourse.getCategories().length > 0
						&& theCourse.getCategories().getClass().isArray()) {

					// This checks if the categories parameter contains numbers
					for (int i = 0; i < theCourse.getCategories().length; i++) {
						String category = String.valueOf(theCourse.getCategories()[i]);
						boolean isCategoryNumeric = true;
						try {
							Integer.parseInt(category);
						} catch (TypeMismatchException e) {
							isCategoryNumeric = false;
						} catch (NumberFormatException e) {
							isCategoryNumeric = false;
						}

						if (!isCategoryNumeric) {
							result += "The 'categories' field must contain numbers. ";
							isOk = false;
							break;
						}
					}

				} else {
					result += "The 'categories' field must contain at least one category index. ";
					isOk = false;
				}

				if (!StringUtils.isBlank(description)) {

					if (description.length() > 4000) {
						result += "The 'content' field must not be longer than 4000 characters. ";
						isOk = false;
					}

				} else {
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

	public boolean validateUserIdCourseId(String userId, String courseId) {

		boolean isOk = true;
		result = "";

		boolean isUserIdInteger = true;
		try {
			Integer.parseInt(userId);
		} catch (TypeMismatchException e) {
			isUserIdInteger = false;
		} catch (NumberFormatException e) {
			isUserIdInteger = false;
		}

		boolean isCourseIdInteger = true;
		try {
			Integer.parseInt(courseId);
		} catch (TypeMismatchException e) {
			isCourseIdInteger = false;
		} catch (NumberFormatException e) {
			isCourseIdInteger = false;
		}

		if (!isUserIdInteger) {
			isOk = false;
			result += "The user ID must be a valid integer. ";
		} else {
			if (Integer.parseInt(userId) <= 0) {
				isOk = false;
				result += "The user ID must be greater than 0";
			}
		}

		if (!isCourseIdInteger) {
			isOk = false;
			result += "The course ID must be a valid integer. ";
		} else {
			if (Integer.parseInt(courseId) <= 0) {
				isOk = false;
				result += "The course ID must be greater than 0";
			}
		}

		return isOk;
	}

}
