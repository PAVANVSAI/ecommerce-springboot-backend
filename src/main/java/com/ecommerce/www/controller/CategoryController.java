package com.ecommerce.www.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.www.entity.Category;
import com.ecommerce.www.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService categoryService;
	public CategoryController(CategoryService categoryService) {
		this.categoryService=categoryService;
	}
	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	@GetMapping("{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryId(id);
	}
	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	@PutMapping("{id}")
	public Category updateCategory(@PathVariable Long id,@RequestBody Category category) {
		Category existingCategory=categoryService.getCategoryId(id);
		if(existingCategory==null) {
			return null;
		}
		existingCategory.setName(category.getName());
		existingCategory.setDescription(category.getDescription());
		return categoryService.createCategory(existingCategory);
	}
	@DeleteMapping("{id}")
	public void deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}
}
