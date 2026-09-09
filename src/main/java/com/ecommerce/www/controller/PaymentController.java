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

import com.ecommerce.www.entity.Payments;
import com.ecommerce.www.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	private PaymentService paymentService;
	public PaymentController(PaymentService PaymentService) {
		this.paymentService=PaymentService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Payments> getByPaymentId(@PathVariable Long id) {
		Payments payment=paymentService.getByPaymentId(id);
		return ResponseEntity.ok(payment);
	}
	@GetMapping
	public ResponseEntity<List<Payments>> getAllPayments() {
		List<Payments> payments=paymentService.getAllPayments();
		return ResponseEntity.ok(payments);
	}
	@PostMapping
	public ResponseEntity <Payments> createPayment(@RequestBody Payments payment) {
		Payments createdPayment=paymentService.createPayment(payment);
		return new ResponseEntity<>(createdPayment,HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity <Void> deletePayment(@PathVariable Long id) {
		paymentService.deletePayment(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public Payments updatePayment(@PathVariable Long id,@RequestBody Payments payment) {
		Payments existingPayment=paymentService.getByPaymentId(id);
		existingPayment.setAmount(payment.getAmount());
		existingPayment.setPaymentMethod(payment.getPaymentMethod());
		existingPayment.setPaymentStatus(payment.getPaymentStatus());
		existingPayment.setPaidAt(payment.getPaidAt());
		return paymentService.createPayment(existingPayment);
	}
}
