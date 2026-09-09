package com.ecommerce.www.service;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.www.dto.LoginRequest;
import com.ecommerce.www.entity.User;
import com.ecommerce.www.exeception.ResourceNotFoundException;
import com.ecommerce.www.repository.UserRepository;


@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JwtService jwtService) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.authenticationManager=authenticationManager;
		this.jwtService=jwtService;
	}
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	public User getUserById(Long Id) {
		return userRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User Not Found With Id: "+Id));
	}
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	public String login(LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
		authenticationManager.authenticate(token);
		return jwtService.generateToken(loginRequest.getEmail());
	}
	public void deleteUser(Long Id) {
		userRepository.deleteById(Id);
	}
	
}
