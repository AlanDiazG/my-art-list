package edu.unam.springsecurity.security.jwt;

import edu.unam.springsecurity.auth.dto.UserInfoDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JWTTokenProvider {
    private String secret;
    private int jwtExpirationInMs;
    private SecretKey key;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.expirationDateInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateJwtToken(Authentication authentication, UserInfoDTO user) {
        Claims claims = Jwts.claims()
                .setSubject("UNAM")
                .setIssuer(user.getUseEmail())
                .setAudience("JAVA");

        // Agrega solamente datos simples y necesarios
        claims.put("userId", user.getUseId());
        claims.put("email", user.getUseEmail());
        claims.put("fullName", user.getUseFirstName() + " " + user.getUseLastName());

        // Agrega las autoridades como una lista de strings
        claims.put("roles", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs * 1000L))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    public Claims getClaims(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getFullName(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        var body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return (String) body.get("issname");
    }

    public String getSubject(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getIssuer(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getIssuer();
    }

    public String getAudience(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getAudience();
    }

    public Date getTokenExpiryFromJWT(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public Date getTokenIatFromJWT(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getIssuedAt();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            key = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException exception) {
            log.error("Invalid JWT token -> Message: {}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            log.error("Expired JWT token -> Message: {}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("Unsupported JWT token -> Message: {}", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("JWT claims string is empty -> Message: {}", exception.getMessage());
        }
        return false;
    }

    public long getExpiryDuration() {
        return jwtExpirationInMs * 1000L;
    }
}