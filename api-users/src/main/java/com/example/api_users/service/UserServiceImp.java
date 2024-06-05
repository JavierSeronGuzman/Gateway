package com.example.api_users.service;

import com.example.api_users.models.UserModel;
import com.example.api_users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    public final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getUserById(String userId) {
        return this.userRepository.findById(Long.parseLong(userId)).orElse(null);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return this.userRepository.findAll();
    }
}
