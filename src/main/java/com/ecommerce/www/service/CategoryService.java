package com.ecommerce.www.service;
import org.springframework.stereotype.Service;
import com.ecommerce.www.entity.Category;
import java.util.List;
import com.ecommerce.www.repository.CategoryRepository;
@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	public Category getCategoryId(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}
