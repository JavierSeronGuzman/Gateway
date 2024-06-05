package com.example.auth.services.impl;


import com.example.auth.common.dto.TokenResponse;
import com.example.auth.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final String secretToken;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretToken) {
        this.secretToken = secretToken;
    }

    @Override
    public TokenResponse generateToken(Long userId) {
        Date expirationDate = new Date(Long.MAX_VALUE);
        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,this.secretToken)
                .compact();
        return TokenResponse.builder()
                .accesToken(token)
                .build();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.secretToken)
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isExpired(String token) {
        try{
            getClaims(token).getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public Integer extractUserId(String token) {
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        }catch (Exception e){
            return null;
        }
    }
}
