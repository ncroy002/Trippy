package com.trippy.back.security;

import com.trippy.back.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtGenerator {
    public String generate(User jwtUser){
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();

        Claims claims = Jwts.claims()
                .setSubject("");
        //todo: possibly change to string value
        claims.put("id", jwtUser.getId());
        claims.put("email", String.valueOf(jwtUser.getEmail()));

        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, 20);
        claims.setExpiration(calendar.getTime());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS384, "P1o2t3A4t5O6")
                .compact();
    }
}
