package com.ecommerce.www.controller;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.www.dto.ProductRequest;
import com.ecommerce.www.entity.Product;
import com.ecommerce.www.service.ProductService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductService productService;
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	@GetMapping("/search")
	public ResponseEntity <List<Product>> getProductByName(@RequestParam String name) {
		List<Product> products = productService.searchProducts(name);
		return ResponseEntity.ok(products);
	}
	@GetMapping("/filter")
	public ResponseEntity<List<Product>> getProductByPrice(@RequestParam BigDecimal minPrice,@RequestParam BigDecimal maxPrice) {
		List<Product> products=productService.filterProductsByPrice(minPrice, maxPrice);
		return ResponseEntity.ok(products);
	}
	@GetMapping("/active")
	public ResponseEntity<List<Product>> getProductByQuery(@RequestParam BigDecimal maxPrice) {
		List<Product> products=productService.queryActiveProductsByMaxPrice(maxPrice);
		return ResponseEntity.ok(products);
	}
	@GetMapping("/page")
	public ResponseEntity<Page<Product>> getProducts(Pageable p) {
		Page<Product> products=productService.getProducts(p);
		return ResponseEntity.ok(products);
	}
	@GetMapping("/search-filter")
	public ResponseEntity<Page<Product>> getProducts(@RequestParam(required=false) String name,@RequestParam(required=false) BigDecimal minPrice,@RequestParam(required=false) BigDecimal maxPrice,Pageable pageable) {
		Page<Product> products= productService.searchProducts(name,minPrice,maxPrice,pageable);
		return ResponseEntity.ok(products);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product=productService.getProductId(id);
		return ResponseEntity.ok(product);
	}
	@GetMapping("/search-cid")
	public ResponseEntity<Page<Product>> getProductwithcid(@RequestParam Long cid,@RequestParam BigDecimal maxPrice,Pageable pageable) {
		Page<Product> products=productService.searchProductsWithCid(cid,maxPrice,pageable);
		return ResponseEntity.ok(products);
	}
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products =productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest productRequest) {
		Product product =productService.createProductRequest(productRequest);
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id,@RequestBody Product product) {
		Product existingProduct=productService.getProductId(id);
		if(existingProduct==null) {
			return null;
		}
		existingProduct.setCategory(product.getCategory());
		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setSetQuantity(product.getStockQuantity());
		existingProduct.setIsActive(product.getisActive());
		return productService.createProduct(existingProduct);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
