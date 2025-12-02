package poly.edu.lab6.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    public String create(UserDetails user, int expirySeconds) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(Map.of("name", "Poly"))
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 1000 * expirySeconds))
                .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Claims getBody(String token) {
        return Jwts.parser()
                .setSigningKey(this.getSigningKey())
                .build().parseClaimsJws(token).getBody();

    }

    public boolean validate(Claims claims) {
        return claims.getExpiration().after(new Date());
    }

    public Key getSigningKey() {
        String secret = "0123456789.0123456789.0123456789";
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //Hàm getSingingKey chỉ dùng để làm bài tập khônng dùng trong dự án thực tế
}
