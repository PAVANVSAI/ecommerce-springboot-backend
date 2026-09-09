package com.ecommerce.www.dto;
import java.math.BigDecimal;

import com.ecommerce.www.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
public class ProductRequest {
	private Category category;
	@NotBlank(message="Name cannot be blank!")
	private String name;
	private String description;
	@Positive(message="Price should be > 0!")
	private BigDecimal price;
	@PositiveOrZero(message="Stock Quantity is not <0!")
	private Integer stockQuantity;
	private Boolean isActive;
	public Category getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setCategory(Category category) {
		this.category=category;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setPrice(BigDecimal price) {
		this.price=price;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity=stockQuantity;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive=isActive;
	}
}
