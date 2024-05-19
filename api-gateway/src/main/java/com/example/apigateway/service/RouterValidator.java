package com.example.apigateway.service;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {
    public static final List<String> openEndpoints = List.of(
            "/auth"
    );
    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest ->
            openEndpoints.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}

