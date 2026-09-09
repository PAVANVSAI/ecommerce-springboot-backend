package com.ecommerce.www.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.www.entity.Addresses;
public interface AddressRepository extends JpaRepository <Addresses,Long> {
	
}
