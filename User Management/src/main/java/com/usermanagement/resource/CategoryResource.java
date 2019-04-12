package com.usermanagement.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.model.Categories;
import com.usermanagement.service.CategoryManager;

@RestController
public class CategoryResource {

	@Autowired
	private CategoryManager categoryManager;
	
	@GetMapping("/category/all")
	public List<Categories> getAll() {
		// usersRepository.flush();
		System.out.println(categoryManager.getCategories());
		return categoryManager.getCategories();
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createCategory(@ModelAttribute("Categories") Categories theCategory) {

		Boolean isOk = categoryManager.validateFields(theCategory);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The following error(s) occurred: "+categoryManager.getResult());
		} else {
			if(!categoryManager.createUpdateCategory(theCategory)){
				return ResponseEntity.status(HttpStatus.CONFLICT).body("The following error(s) occurred: "+categoryManager.getResult());
			}
			else{
				return ResponseEntity.status(HttpStatus.OK).body(theCategory);
			}
		}
	}
}
