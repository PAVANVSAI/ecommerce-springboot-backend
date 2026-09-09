package com.ecommerce.www.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.www.dto.ProductRequest;
import com.ecommerce.www.entity.Category;
import com.ecommerce.www.entity.Product;
import com.ecommerce.www.exeception.ResourceNotFoundException;
import com.ecommerce.www.repository.CategoryRepository;
import com.ecommerce.www.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filterProductsByPrice(
            BigDecimal minPrice, BigDecimal maxPrice) {

        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> queryActiveProductsByMaxPrice(BigDecimal maxPrice) {
        return productRepository.findActiveProductsByMaxPrice(maxPrice);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> searchProducts(
            String name,
            BigDecimal minP,
            BigDecimal maxP,
            Pageable p) {

        return productRepository.searchProducts(name, minP, maxP, p);
    }

    public Page<Product> searchProductsWithCid(
            Long cid,
            BigDecimal maxP,
            Pageable p) {

        return productRepository.searchProductwithcid(cid, maxP, p);
    }

    public Product getProductId(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Product Not Found With Id: " + id));
    }

    public Product createProduct(Product product) {

        Long categoryId = product.getCategory().getCategoryId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Category Not Found With Id: " + categoryId));

        product.setCategory(category);

        return productRepository.save(product);
    }

    public Product createProductRequest(ProductRequest productRequest) {

        Product product = new Product();

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setSetQuantity(productRequest.getStockQuantity());
        product.setIsActive(productRequest.getIsActive());

        // Get the category ID from the Category object
        Long categoryId = productRequest.getCategory().getCategoryId();

        // Find the actual category from database
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Category Not Found With Id: " + categoryId));

        product.setCategory(category);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}