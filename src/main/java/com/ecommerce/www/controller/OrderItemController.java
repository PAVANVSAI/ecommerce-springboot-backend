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

import com.ecommerce.www.entity.OrderItems;
import com.ecommerce.www.service.OrderItemService;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItems>> getAllOrderItems() {
        List<OrderItems> orderItems = orderItemService.getAllOrderItem();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable Long id) {
        OrderItems orderItem = orderItemService.getByOrderItemId(id);
        return ResponseEntity.ok(orderItem);
    }

    @PostMapping
    public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItems orderItems) {
        OrderItems createdOrderItem =orderItemService.createOrderItem(orderItems);
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItems> updateOrderItem(@PathVariable Long id,@RequestBody OrderItems orderItems) {
        OrderItems existing =orderItemService.getByOrderItemId(id);

        
        existing.setQuantity(orderItems.getQuantity());
        existing.setUnitPrice(orderItems.getUnitPrice());
        OrderItems updated =orderItemService.createOrderItem(existing);
        return ResponseEntity.ok(updated);
    }
}