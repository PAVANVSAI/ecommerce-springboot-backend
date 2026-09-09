package com.ecommerce.www.service;

import org.springframework.stereotype.Service;
import com.ecommerce.www.repository.PaymentRepository;
import com.ecommerce.www.exeception.ResourceNotFoundException;
import com.ecommerce.www.entity.Payments;
import com.ecommerce.www.entity.Order;
import com.ecommerce.www.repository.OrderRepository;
import java.util.List;
@Service
public class PaymentService {
	private PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	public PaymentService(PaymentRepository paymentRepository,OrderRepository orderRepository) {
		this.paymentRepository=paymentRepository;
		this.orderRepository=orderRepository;
	}
	
	public List<Payments> getAllPayments() {
		return paymentRepository.findAll();
	}
	public Payments getByPaymentId(Long id) {
		return paymentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not Found With Payment Id: "+id));
	}
	public Payments createPayment(Payments payment) {
		Long orderId = payment.getOrder().getOrderId();
		Order order=orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Not Found With Order Id: "+orderId));
		payment.setOrder(order);
		return paymentRepository.save(payment);
	}
	public void deletePayment(Long id) {
		paymentRepository.deleteById(id);
	}
}
