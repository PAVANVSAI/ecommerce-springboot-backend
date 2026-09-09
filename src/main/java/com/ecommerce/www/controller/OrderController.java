package com.ecommerce.www.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.www.entity.Order;
import com.ecommerce.www.service.OrderService;
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private OrderService orderService;
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Order order=orderService.getOrderById(id);
		return ResponseEntity.ok(order);
	}
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders=orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order createdOrder=orderService.createOrder(order);
		return new ResponseEntity<>(createdOrder,HttpStatus.CREATED);
	}
	@PutMapping("/{id}") 
	public Order updateOrder(@PathVariable Long id,@RequestBody Order order) {
		Order exisistingOrder=orderService.getOrderById(id);
		exisistingOrder.setStatus(order.getStatus());
		exisistingOrder.setUser(order.getUser());
		exisistingOrder.setTotalAmount(order.getTotalAmount());
		exisistingOrder.setCreatedAt(order.getCreatedAt());
		exisistingOrder.setAddress(order.getAddress());
		return orderService.createOrder(exisistingOrder);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}
	
}
