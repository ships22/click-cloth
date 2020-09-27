package com.clickndcloth.server_side.testunitaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.clickndcloth.server_side.application.CategoriesManager;
import com.clickndcloth.server_side.models.Categories;
import com.clickndcloth.server_side.services.CategoriesDomainServiceImpl;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Categoriesunittest {
	
	@Autowired
	private CategoriesManager categoriesManager;
	
	@MockBean
	private CategoriesDomainServiceImpl categoriesService;
	
	// test get all category -
		
		@Test
		void testGetAllCategories() {
			Categories category = new Categories();
			category.setId(10);
			category.setName("test Cat");
			when(categoriesService.getAllCategories()).thenReturn((List<Categories>) Stream.of(category).collect(Collectors.toList()));
			assertEquals(1 , categoriesManager.getCategories().size());
		}
		
		// test get add category -
		@Test 
		void testAddCategories() {
			Categories category = new Categories();
			category.setName("test Cat");
			when(categoriesService.addCategory(category)).thenReturn(category);
			assertEquals(category, categoriesService.addCategory(category));
		}
		
		// test get delete category -
		@Test
		void testDeleteCategory() {
			int idCategory = 5;
			categoriesManager.deleteCategory(idCategory);
			verify(categoriesService, times(1)).deleteCategory(idCategory);
		}

}
