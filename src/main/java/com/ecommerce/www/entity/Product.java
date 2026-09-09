package com.ecommerce.www.entity;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="products",schema="ecommerce")
public class Product{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long productId;
	
	@ManyToOne
	@JoinColumn(name="category_id",nullable=false)
	private Category category;
	@Column(nullable=false)
	private String name;

	private String description;
	@Column(nullable=false,precision=10,scale=2)
	private BigDecimal price;
	@Column(name="stock_quantity",nullable=false)
	private Integer stockQuantity;
	@Column(name="is_active",nullable=false)
	private Boolean isActive;
	@Column(name="created_at")
	private OffsetDateTime createdAt;
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
	public void setSetQuantity(Integer stockQuantity) {
		this.stockQuantity=stockQuantity;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive=isActive;
	}
	public void setCreatedAt (OffsetDateTime createdAt) {
		this.createdAt=createdAt;
	}
	public Long getProductId() {
		return productId;
	}
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
	public Boolean getisActive() {
		return isActive;
	}
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
}


