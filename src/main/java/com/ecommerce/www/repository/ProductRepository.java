package com.ecommerce.www.repository;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.www.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	List<Product> findByNameContainingIgnoreCase(String name);
	List<Product> findByPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);
	@Query("SELECT p FROM Product p WHERE p.isActive=true AND p.price<=:maxPrice")
	List<Product> findActiveProductsByMaxPrice(@Param("maxPrice") BigDecimal maxPrice);
	@Query("SELECT p FROM Product p WHERE "+"(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name,'%'))) AND " +"(:minPrice IS NULL OR p.price>=:minPrice) AND"+"(:maxPrice IS NULL OR p.price<=:maxPrice)")
	Page<Product> searchProducts(@Param("name") String name,@Param("minPrice" ) BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice,Pageable pageable);
	@Query("SELECT p FROM Product p WHERE p.category.categoryId=:categoryId AND p.price<=:maxPrice ")
	Page<Product> searchProductwithcid(@Param("categoryId") Long categoryId,@Param("maxPrice") BigDecimal maxPrice,Pageable pageable);
}