package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.dto.CategoriesDto;
import com.clickndcloth.server_side.models.Categories;
import com.clickndcloth.server_side.services.CategoriesDomainServiceImpl;

@Service
public class CategoriesManager {

	@Autowired
	private CategoriesDomainServiceImpl categoriesDomainService;
	
	@Transactional
	public CategoriesDto addCategory(Categories category) {
		Categories addedCategories = categoriesDomainService.addCategory(category);
		CategoriesDto categoriesDto = new CategoriesDto();
		categoriesDto.setId(addedCategories.getId());
		categoriesDto.setName(addedCategories.getName());
		return categoriesDto;
	}
	
	@Transactional
	public List<CategoriesDto>getCategories() {
		List<Categories>categoryList = categoriesDomainService.getAllCategories();
		List<CategoriesDto>categoriesDtos = new ArrayList<CategoriesDto>();
		categoryList.forEach(category -> {
			CategoriesDto categoriesDto = new CategoriesDto();
			categoriesDto.setId(category.getId());
			categoriesDto.setName(category.getName());
			categoriesDtos.add(categoriesDto);
		});
		return categoriesDtos;
	}
	
	@Transactional
	public CategoriesDto getCategoryByID(Integer id) {
		Categories category = categoriesDomainService.getCategoryByID(id);
		CategoriesDto categoriesDto = new CategoriesDto();
		categoriesDto.setId(category.getId());
		categoriesDto.setName(category.getName());
		return categoriesDto;
	}
	
	@Transactional
	public CategoriesDto updateCategory(Categories category) {
		Categories updateCategories = categoriesDomainService.updateCategory(category);
		CategoriesDto categoriesDto = new CategoriesDto();
		categoriesDto.setId(updateCategories.getId());
		categoriesDto.setName(updateCategories.getName());
		return categoriesDto;
	}
	
	@Transactional
	public String deleteCategory(Integer id) {
		return categoriesDomainService.deleteCategory(id);
	}
}
