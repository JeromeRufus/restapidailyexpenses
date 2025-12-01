package com.dailyexpenses.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dailyexpenses.dto.request.CreateUserRequest;
import com.dailyexpenses.dto.response.UserResponse;
import com.dailyexpenses.model.Users;
import com.dailyexpenses.repository.UserRepository;
import com.dailyexpenses.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Spring will inject UserRepository automatically
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        Users users = new Users();
        users.setName(request.getName());
        users.setEmail(request.getEmail());
        users.setMobile(request.getMobile());

        Users saved = userRepository.save(users);

        return new UserResponse(saved.getId(), saved.getName(), saved.getEmail(),saved.getMobile());
    }

    @Override
    public UserResponse getUser(String id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found " + id));
        return new UserResponse(user.getId(), user.getName(), user.getMobile(), user.getEmail());
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getMobile(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
