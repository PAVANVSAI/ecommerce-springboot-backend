package com.ecommerce.www.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.www.entity.Payments;
public interface PaymentRepository extends JpaRepository<Payments,Long>{
	
}
