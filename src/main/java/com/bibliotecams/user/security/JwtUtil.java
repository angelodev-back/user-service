package com.bibliotecams.user.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION = 1000 * 60 * 60 * 8;
    
    
    public String generarToken(String email, String rol) {
    	return Jwts.builder()
    			.setSubject(email)
    			.claim("rol", rol)
    			.setIssuedAt(new Date())
    			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
    			.signWith(key)
    			.compact();
    }
    
    public String extractEmail(String token) {
    	return Jwts.parserBuilder()
    			.setSigningKey(key)
    			.build()
    			.parseClaimsJws(token)
    			.getBody()
    			.getSubject();
    }
	
    public String extractRol(String token) {
    	return Jwts.parserBuilder()
    			.setSigningKey(key)
    			.build()
    			.parseClaimsJws(token)
    			.getBody()
    			.get("rol", String.class);
    }
    
    public boolean isTokenaValid(String token) {
    	try {
    		Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    		return true;
    	}catch(Exception ex) {
    		return false;
    	}
    }
}
