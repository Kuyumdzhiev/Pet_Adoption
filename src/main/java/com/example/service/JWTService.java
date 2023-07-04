package com.example.service;

import com.example.persistence.entities.UserSecurityEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JWTService {
    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims, UserSecurityEntity userSecurityEntity);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
