package com.example.auth.controller;


import com.example.auth.common.constants.ApiPathConstants;
import com.example.auth.common.dto.TokenResponse;
import com.example.auth.common.dto.UserRequest;
import com.example.auth.common.entities.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {
    @PostMapping(value = "/register")
    ResponseEntity<TokenResponse> createUser(@RequestBody @Valid UserRequest userRequest);
    @PostMapping(value = "/login")
    ResponseEntity<TokenResponse> login(@RequestBody @Valid UserRequest userRequest);
}
