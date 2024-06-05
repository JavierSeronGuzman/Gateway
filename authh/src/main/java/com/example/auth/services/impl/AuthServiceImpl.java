package com.example.auth.services.impl;

import com.example.auth.common.dto.TokenResponse;
import com.example.auth.common.dto.UserRequest;
import com.example.auth.common.entities.UserModel;
import com.example.auth.services.JwtService;
import com.example.auth.repository.UserRepository;
import com.example.auth.services.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private  final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::maptoEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("Error al crear usuario"));
    }

    @Override
    public TokenResponse login(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::getUserByEmailAndPassword)
                .map(UserModel::getId)
                .map(jwtService::generateToken)
                .orElseThrow(()-> new RuntimeException("Error Login User"));

    }

    public UserModel getUserByEmailAndPassword(UserRequest givenUser) {
        return userRepository.findByEmailAndPassword(givenUser.getEmail(), givenUser.getPassword())
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    private UserModel maptoEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role("USER")
                .build();

    }


}
