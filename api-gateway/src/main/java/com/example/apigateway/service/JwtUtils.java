package com.example.apigateway.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    private final String secretKey = "javier";

    public Claims getCLaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isExpired(String token){
        try{
            return getCLaims(token).getExpiration().before(new Date());
        } catch (Exception e){
            return  true;
        }
    }
    public Integer extractUserId(String token){
        try{
            return Integer.parseInt(getCLaims(token).getSubject());
        } catch (Exception e){
            return  null;
        }
    }
}

