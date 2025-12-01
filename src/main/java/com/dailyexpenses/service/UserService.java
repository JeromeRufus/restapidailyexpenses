package com.dailyexpenses.service;

import java.util.List;

import com.dailyexpenses.dto.request.CreateUserRequest;
import com.dailyexpenses.dto.response.UserResponse;

public interface UserService {
	
	UserResponse createUser(CreateUserRequest request);
	UserResponse getUser(String id);
	List<UserResponse> getAllUsers(); 

}
