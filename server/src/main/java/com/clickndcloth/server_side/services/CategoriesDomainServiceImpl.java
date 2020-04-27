package com.clickndcloth.server_side.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.models.Categories;
import com.clickndcloth.server_side.repository.CategoriesRepository;

@Component
public class CategoriesDomainServiceImpl implements CategoriesDomain {

	@Autowired
	private CategoriesRepository categoriesRepository; 
	
	@Override
	public Categories addCategory(Categories category) {
		return categoriesRepository.save(category);
	}

	@Override
	public List<Categories> getAllCategories() {
		return categoriesRepository.findAll();
	}
	
	@Override
	public Categories getCategoryByID(Integer id) {
		return categoriesRepository.getOne(id);
	}

	@Override
	public Categories updateCategory(Categories category) {
		return categoriesRepository.saveAndFlush(category);
	}

	@Override
	public String deleteCategory(Integer id) {
		categoriesRepository.deleteById(id);
		return  "Category with id : " + id + " has been deleted successfully";
	}

	

}
