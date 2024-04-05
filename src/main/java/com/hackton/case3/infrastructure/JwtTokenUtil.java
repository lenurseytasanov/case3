package com.hackton.case3.infrastructure;

import com.hackton.case3.domain.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    Date now = new Date();

    public SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusHours(1).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(refreshExpirationInstant);

        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof Employee customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("username", customUserDetails.getUsername());
            claims.put("password", customUserDetails.getPassword());
            claims.put("role", customUserDetails.getRole());
        }
        return Jwts.builder().claims(claims).subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSecretKey()).compact();

    }

    public boolean validateToken(final String token) {
        return getClaims(token)
                .getExpiration()
                .after(new Date (System.currentTimeMillis()));
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserName(String token) {
        return getClaims(token).getSubject();
    }
}
