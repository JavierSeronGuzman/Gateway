package com.example.auth.controller.impl;

import com.example.auth.common.dto.TokenResponse;
import com.example.auth.common.dto.UserRequest;
import com.example.auth.common.entities.UserModel;
import com.example.auth.controller.AuthApi;
import com.example.auth.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> login(UserRequest userRequest) {
        TokenResponse tokenResponse = authService.login(userRequest);
        return ResponseEntity.ok(tokenResponse);
    }

}
