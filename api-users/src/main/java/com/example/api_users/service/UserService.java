package com.example.api_users.service;

import com.example.api_users.models.UserModel;

import java.util.List;

public interface UserService {
    UserModel getUserById(String userId);

    List<UserModel> getAllUsers();
}
