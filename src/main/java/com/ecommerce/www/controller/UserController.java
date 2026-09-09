package com.ecommerce.www.controller;
import java.util.List;
import com.ecommerce.www.dto.LoginRequest;
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

import com.ecommerce.www.entity.User;
import com.ecommerce.www.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService=userService;
	}
	@GetMapping("/{id}")
	public ResponseEntity <User> getUserById(@PathVariable Long id) {
		User user=userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users=userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser=userService.createUser(user);
		return new ResponseEntity<> (createdUser,HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		String token=userService.login(loginRequest);
		return  ResponseEntity.ok(token);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity <Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id,@RequestBody User user ) {
		User existingUser=userService.getUserById(id);
		
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setPhone(user.getPhone());
		existingUser.setIsActive(user.getIsActive());
		existingUser.setCreatedAt(user.getCreatedAt());
		return userService.createUser(existingUser);
	}
}
