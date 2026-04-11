package com.bibliotecams.user.security;

import com.bibliotecams.user.constants.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generarToken(String email) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + AppConstants.JWT_EXPIRACION_MS);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(key)
                .compact();
    }

    public String extraerEmail(String token) {
        return extraerClaims(token).getSubject();
    }

    private Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean esTokenValido(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean estaExpirado(String token) {
        try {
            Date expiracion = extraerClaims(token).getExpiration();
            return expiracion.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}