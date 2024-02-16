package com.til.security;

import com.til.dto.MemberLoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPRIATION_DATE = 1000 * 60 * 60;

    public String generationToken(MemberLoginDto memberLoginDto) {
        Claims claims = Jwts.claims().setSubject(memberLoginDto.getEmail());

        Date now = new Date();
        Date ExpireDate = new Date(now.getTime()+EXPRIATION_DATE);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(ExpireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
