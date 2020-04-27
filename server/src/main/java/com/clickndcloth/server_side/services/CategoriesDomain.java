package com.clickndcloth.server_side.services;

import java.util.List;

import com.clickndcloth.server_side.models.Categories;

public interface CategoriesDomain {
	
	Categories addCategory(Categories category);
	
	List<Categories> getAllCategories();
	
	Categories getCategoryByID(Integer id);
	
	Categories updateCategory(Categories category);
	
	String deleteCategory(Integer id);

}
