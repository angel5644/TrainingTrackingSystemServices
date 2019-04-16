package com.usermanagement.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//List Category
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> listCategories() {
		return ResponseEntity.status(HttpStatus.OK).body(categoryManager.listCategories());		
	}
}