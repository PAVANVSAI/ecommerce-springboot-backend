package com.ecommerce.www.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="payments",schema="ecommerce")
public class Payments {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_id")
	private Long paymentId;
	@OneToOne
	@JoinColumn(name="order_id")
	private Order order;
	@Column(name="amount")
	private BigDecimal amount;
	@Column(name="payment_method")
	private String paymentMethod;
	@Column(name="payment_status")
	private String paymentStatus;
	@Column(name="paid_at")
	private OffsetDateTime paidAt;
	public void setOrder(Order order) {
		this.order=order;
	}
	public void setAmount(BigDecimal amount) {
		this.amount=amount;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod=paymentMethod;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus=paymentStatus;
	}
	public void setPaidAt(OffsetDateTime paidAt) {
		this.paidAt=paidAt;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public Order getOrder() {
		return order;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public OffsetDateTime getPaidAt() {
		return paidAt;
	}
}
