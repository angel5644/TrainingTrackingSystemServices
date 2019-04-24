package com.usermanagement.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//Create Category
	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> updateCategory(@PathVariable("id") final Integer id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "description", required = true) String description) {

		Boolean isOk = categoryManager.validateFields(name,description);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The following error(s) occurred: "+categoryManager.getResult());
		} else {
			Categories theCategory = new Categories();
			theCategory.setName(name.trim().toUpperCase());
			theCategory.setDescription(description.trim().toUpperCase());
			theCategory.setId(id);
			if(!categoryManager.updateCategory(theCategory)){
				return ResponseEntity.status(HttpStatus.CONFLICT).body("The following error(s) occurred: "+categoryManager.getResult());
			}
			else{
				return ResponseEntity.status(HttpStatus.OK).body(theCategory);
			}
		}
	}
}
