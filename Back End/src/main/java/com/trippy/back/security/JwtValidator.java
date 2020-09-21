package com.trippy.back.security;

import com.trippy.back.entities.User;
import com.trippy.back.repos.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtValidator {
    @Autowired
    UserRepo userRepo;

    private String secret = "P1o2t3A4t5O6";

    public User validate(String token){
        User jwtUser = null;
        Date expirationDate = null;
        Date current = new Date();
        try{
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            jwtUser = new User();
            jwtUser.setId((Long) body.get("id"));
            jwtUser.setEmail((String) body.get("email"));
            expirationDate = body.getExpiration();

            if(userRepo.findByToken(token) == null || current.after(expirationDate)){
                jwtUser = null;
                throw new Exception("JWT does not exist or is expired");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
        return  jwtUser;
    }
}
