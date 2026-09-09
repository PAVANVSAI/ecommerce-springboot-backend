package com.ecommerce.www.service;
import com.ecommerce.www.repository.OrderItemRepository;
import java.util.List;
import com.ecommerce.www.exeception.ResourceNotFoundException;
import com.ecommerce.www.entity.OrderItems;
import com.ecommerce.www.repository.OrderRepository;
import com.ecommerce.www.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.ecommerce.www.entity.Order;
import com.ecommerce.www.entity.Product;

@Service
public class OrderItemService {
	private OrderItemRepository orderItemRepository;
	private OrderRepository orderRepository;
	private ProductRepository productRepository;
	public OrderItemService(OrderItemRepository orderItemRepository,OrderRepository orderRepository,ProductRepository productRepository) {
		this.orderItemRepository=orderItemRepository;
		this.orderRepository=orderRepository;
		this.productRepository=productRepository;
	}
	public List<OrderItems> getAllOrderItem() {
		return orderItemRepository.findAll();
	}
	public OrderItems getByOrderItemId(Long id) {
		return orderItemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("OrderItem Not Found With Id: "+id));
	}
	public OrderItems createOrderItem(OrderItems orderitems) {
		Long orderId = orderitems.getOrder().getOrderId();
		Order order=orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Found With Id: "+orderId));
		Long productId = orderitems.getProduct().getProductId();

		Product product = productRepository.findById(productId).orElseThrow(() ->new ResourceNotFoundException("Product Not Found With Id: " + productId));
		orderitems.setOrder(order);
		orderitems.setProduct(product);
		return orderItemRepository.save(orderitems);
	}
	public void deleteOrderItem(Long id) {
		orderItemRepository.deleteById(id);
	}
	
}
