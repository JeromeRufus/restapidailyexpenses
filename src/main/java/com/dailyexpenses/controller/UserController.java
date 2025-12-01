package com.dailyexpenses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailyexpenses.dto.request.CreateUserRequest;
import com.dailyexpenses.dto.response.UserResponse;
import com.dailyexpenses.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	private final UserService userService;
	
	
	public UserController(UserService userService) {
        this.userService = userService;
        
    }
	
	@PostMapping
	public UserResponse createUser(@Valid  @RequestBody CreateUserRequest request) {
		return userService.createUser(request);
	}
	
	@GetMapping("/{id}")
	public UserResponse getUser(@PathVariable String id) {
		return userService.getUser(id);
	}
	
	
	@GetMapping
	public List<UserResponse> getAllUser() {
		return userService.getAllUsers();
		 	
	}
	
	
	
	
	

	
	
}
