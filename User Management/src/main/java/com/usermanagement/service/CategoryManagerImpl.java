package com.usermanagement.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.usermanagement.model.Categories;
import com.usermanagement.repository.CategoryRepository;

@Service
public class CategoryManagerImpl implements CategoryManager {

	@Autowired
	CategoryRepository categoryRepository;

	private String result;

	@Override
	@Transactional
	public List<Categories> getCategories() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional
	public Boolean updateCategory(Categories theCategory) throws DuplicateKeyException {
		result = "";
		Boolean isOk = true;
		try {
			Categories category = (categoryRepository.findById(theCategory.getId()).isPresent())
					? categoryRepository.findById(theCategory.getId()).get() : null;

			if (category == null) {
				result = " The category to be edited was not found in the database. ";
				isOk = false;
			} else {
				if (categoryRepository.findDuplicate(theCategory.getName(), theCategory.getId()).size() > 0) {
					result = " There is already a category with that name. ";
					isOk = false;
				} else {
					if (!StringUtils.isEmpty(theCategory.getName().trim().toUpperCase())) {
						category.setName(theCategory.getName().trim().toUpperCase());
					}
					if (!StringUtils.isEmpty(theCategory.getDescription().trim().toUpperCase())) {
						category.setDescription(theCategory.getDescription().trim().toUpperCase());
					}
					categoryRepository.save(category);
				}
			}

			if (!isOk) {
				result = "The following error(s) occurred: " + result;
				throw new DuplicateKeyException(result);
			}
		} catch (DuplicateKeyException ex) {
			System.out.println(ex);
			return false;
		}
		return isOk;
	}

	public Boolean validateFields(String name, String description) throws InvalidParameterException {
		Boolean isOk = true;
		result = "";

		try {

			if (StringUtils.isBlank(name) && StringUtils.isBlank(description)) {

				result += "There are no fields to be edited. ";
				isOk = false;

			} else {

				if (!StringUtils.isBlank(name)) {
					if (name.length() > 50) {
						result += "The 'name' field has more than 50 characters. ";
						isOk = false;
					} else {
						Pattern specialChars = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
						Matcher hasSpecialChars = specialChars.matcher(name);

						if (hasSpecialChars.find()) {
							result += "The 'name' field doesn't accept special characters. ";
							isOk = false;
						}
					}
				}

				if (!StringUtils.isBlank(description)) {
					if (description.length() > 500) {
						result += "The 'description' field has more than 500 characters. ";
						isOk = false;
					}
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

	public String getResult() {
		return this.result;
	}
}
