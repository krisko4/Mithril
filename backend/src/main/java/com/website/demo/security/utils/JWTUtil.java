package com.website.demo.security.utils;


import com.website.demo.authorities.AppUserRole;
import com.website.demo.user.AppUser;
import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;


    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        if(token == null){
            throw new IllegalArgumentException("Invalid token");
        }
        if(isTokenExpired(token)){
            throw new IllegalArgumentException("Token has expired");
        }
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    //validate token
    public Boolean validateToken(String token, AppUser appUser) {
        final String username = getUsernameFromToken(token);
        return (username.equals(appUser.getUsername()) && !isTokenExpired(token));
    }



    public String generateToken(AppUser appUser) {
        Claims claims = Jwts.claims().setSubject(appUser.getEmail());
        claims.put("userId", appUser.getId() + "");
        claims.put("role", appUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public AppUser parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            AppUser user = new AppUser();
            user.setEmail(body.getSubject());
            user.setId(Long.parseLong((String) body.get("userId")));
            user.setRole((AppUserRole) body.get("role"));
            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

}
