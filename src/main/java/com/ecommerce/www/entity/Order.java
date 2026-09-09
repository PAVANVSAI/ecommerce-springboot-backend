package com.ecommerce.www.entity;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders",schema="ecommerce")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id",nullable=false)
	private Long orderId;
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="address_id",nullable=false)
	private Addresses address;
	@Column(name="status",nullable=false)
	private String status;
	@Column(name="total_amount",nullable=false)
	private BigDecimal totalAmount;
	@Column(name="created_at",nullable=false)
	private OffsetDateTime createdAt;
	public void setUser(User user) {
		this.user=user;
	}
	public void setAddress(Addresses address) {
		this.address=address;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount=totalAmount;
	}
	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt=createdAt;
	}
	public Long getOrderId() {
		return orderId;
	}
	public User getUser() {
		return user;
	}
	public Addresses getAddress() {
		return address;
	}
	public String getStatus() {
		return status;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
}
