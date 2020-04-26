package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.application.CategoriesManager;
import com.clickndcloth.server_side.dto.CategoriesDto;
import com.clickndcloth.server_side.models.Categories;


@RestController
@RequestMapping(value = "/api")
public class CategoriesController {
	
	@Autowired
	private CategoriesManager categoriesManager;
	
	@GetMapping(value = "/categories")
	public List<CategoriesDto> getAllCategories() {
		return categoriesManager.getCategories();
	}
	

	@GetMapping(value = "/category/{category_id}")
	public CategoriesDto getCategoryById(@PathVariable ("category_id") Integer category_id) {
		return categoriesManager.getCategoryByID(category_id);
	}
	
	
	@PostMapping(value = "/add_category", produces = "application/json")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CategoriesDto addCategory(@RequestBody Categories category) {
		return categoriesManager.addCategory(category);
		
	}
	
	@PutMapping(value = "/update_category")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CategoriesDto updateCategories(@RequestBody Categories category) {
		return categoriesManager.updateCategory(category);
	}
	
	@DeleteMapping(value = "/delete_category/{category_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteCategory(@PathVariable ("category_id") Integer category_id) {
		return categoriesManager.deleteCategory(category_id);
	}

}
