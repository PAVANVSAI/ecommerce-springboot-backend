package com.ecommerce.www.repository;
import com.ecommerce.www.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
}
