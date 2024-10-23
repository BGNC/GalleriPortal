package com.bgnc.galleriportal.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


import static com.bgnc.galleriportal.util.Helper.getKey;


@Service
public class JWTService {


    public String generateToken(UserDetails userDetails) {


        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public <T> T exportToken(String token, Function<Claims, T> claimsFunct) {
        Claims claims = getClaims(token);
        return claimsFunct.apply(claims);
    }
    public String getUsernameByToken(String token) {
        return exportToken(token, Claims::getSubject);
    }

    public boolean tokenIsValid(String token) {
        Date expireDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);

    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

}
