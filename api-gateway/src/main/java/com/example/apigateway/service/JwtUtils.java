package com.example.apigateway.service;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    private final String secretKey = "sdkajdlksajdslakdjaslkdjsalkdjsadheskajdlaskjdaslkdjaldjasldjsakdjasldkjasld";

    public Claims getCLaims(String token){
        int i = token.lastIndexOf('.');
        String withoutSignature = token.substring(0, i+1);
        Jwt<Header, Claims> untrusted = Jwts.parserBuilder().build().parseClaimsJwt(withoutSignature);
        System.out.println(untrusted.getBody());
        return untrusted.getBody();
    }
    public boolean isExpired(String token){
        try{
            return getCLaims(token).getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }
    public Integer extractUserId(String token){
        try{
            System.out.println(getCLaims(token).getSubject());
            return Integer.parseInt(getCLaims(token).getSubject());
        } catch (Exception e){
            return  null;
        }
    }
}

