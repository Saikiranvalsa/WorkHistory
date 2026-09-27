package com.saikiran.WorkHistory.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenService {
    @Value(("${jwt.key}"))
    private String key;
    public String genearateJwtToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*10000))
                .signWith(generateKey())
                .compact();
    }
    public Key generateKey(){
        byte[] secretKey= Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(secretKey);
    }
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(UserDetails userDetails, String token) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

}
