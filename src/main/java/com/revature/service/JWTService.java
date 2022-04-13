package com.revature.service;

import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTService {

    private static JWTService instance;

    private Key key;

    public JWTService() {
        byte[] secret = "my_secret_password_asdfasdfjkljclkvjl13432k2312jlkj3941809df".getBytes();
        key = Keys.hmacShaKeyFor(secret);
    }

    public static JWTService getInstance() {
        if (JWTService.instance == null) {
            JWTService.instance = new JWTService();
        }

        return JWTService.instance;
    }

    public String createJWT(User user) {
        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("user_id", user.getUser_id())
                .claim("user_role", user.getUser_role())
                .signWith(key)
                .compact();

        return jwt;
    }

    public Jws<Claims> parseJwt(String jwt) {
        try {
            Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

            return token;
        } catch(JwtException e) {
            e.printStackTrace();
            throw new UnauthorizedResponse("JWT was invalid");
        }

    }

}