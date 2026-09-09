package com.ecommerce.www.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.www.entity.User;
import com.ecommerce.www.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).authorities(user.getRole()).build();
	}
	
	
}
