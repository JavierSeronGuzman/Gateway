package com.example.auth.services;

import com.example.auth.common.dto.TokenResponse;
import com.example.auth.common.dto.UserRequest;

public interface AuthService {
    TokenResponse createUser(UserRequest userRequest);

    TokenResponse login(UserRequest userRequest);

}
