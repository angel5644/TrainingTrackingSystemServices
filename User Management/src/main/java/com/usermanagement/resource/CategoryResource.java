package com.usermanagement.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.usermanagement.model.Categories;
import com.usermanagement.service.CategoryManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(maxAge = 3600)
@Api(value="Category Management System")
@RestController
public class CategoryResource {

	@Autowired
	private CategoryManager categoryManager;

	@ApiOperation(value = "View a list of available categories", response = List.class)
	@GetMapping("/category/all")
	public List<Categories> getAll() {
		// usersRepository.flush();
		System.out.println(categoryManager.getCategories());
		return categoryManager.getCategories();
	}

	// Create Category
	@ApiOperation(value = "Creates a new category", response = ResponseEntity.class)
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createCategory(@ModelAttribute("Categories") Categories theCategory) {

		theCategory.setName(theCategory.getName().trim().toUpperCase());
		theCategory.setDescription(theCategory.getDescription().trim().toUpperCase());
		Boolean isOk = categoryManager.validateFields(theCategory);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("The following error(s) occurred: " + categoryManager.getResult());
		} else {
			if (!categoryManager.createCategory(theCategory)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("The following error(s) occurred: " + categoryManager.getResult());
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(theCategory);
			}
		}
	}

	// Update Category
	@ApiOperation(value = "Edit an existing category", response = ResponseEntity.class)
	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> updateCategory(@PathVariable("id") final Integer id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description) {

		name = (name == null) ? "" : name;
		description = (description == null) ? "" : description;
		Boolean isOk = categoryManager.validateFields(name, description);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("The following error(s) occurred: " + categoryManager.getResult());
		} else {
			Categories theCategory = new Categories();
			theCategory.setName(name.trim().toUpperCase());
			theCategory.setDescription(description.trim().toUpperCase());
			theCategory.setId(id);
			if (!categoryManager.updateCategory(theCategory)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("The following error(s) occurred: " + categoryManager.getResult());
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(theCategory);
			}
		}
	}

	// List Category
	@ApiOperation(value = "Performs a custom search to find specific(s) categorie(s)", response = ResponseEntity.class)
	@RequestMapping(value = "/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> listCategories(@RequestParam(value = "searchField", required = false) String searchField,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
			@RequestParam(value = "orderType", defaultValue = "asc", required = false) String orderType,
			@RequestParam(value = "pageNo", defaultValue = "1", required = false) String pageNo,
			@RequestParam(value = "numberRec", defaultValue = "10", required = false) String numberRec) {

		boolean isOk = categoryManager.validateSearchFields(
				(searchField == null) ? "" : searchField.toUpperCase().trim(),
				(searchValue == null) ? "" : searchValue.toUpperCase().trim(), orderBy.toUpperCase().trim(),
				orderType.toUpperCase().trim(), pageNo, numberRec);

		if (!isOk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categoryManager.getResult());
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(categoryManager.findCategories((searchField == null) ? "" : searchField.toUpperCase().trim(),
							(searchValue == null) ? "" : searchValue.toUpperCase().trim(), orderBy.toUpperCase().trim(),
							orderType.toUpperCase().trim(), Integer.valueOf(pageNo), Integer.valueOf(numberRec)));
		}
	}
}
