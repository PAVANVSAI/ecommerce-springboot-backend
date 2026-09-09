package com.ecommerce.www.repository;
import com.ecommerce.www.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order,Long> {
	
}
