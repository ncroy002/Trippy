package com.trippy.back.security;

import com.trippy.back.entities.Account;
import com.trippy.back.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class JwtAuthenicationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private JwtValidator jwtValidator;


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken)usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();
        Account jwtAccount = jwtValidator.validate(token);
        if(jwtAccount == null){
            throw new RuntimeException("Invalid JWT");
        }
        String role = "0";
        try {

            Admin adminUser = (Admin)jwtAccount;
            role = adminUser.getRole();
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
        return new JwtUserDetails(jwtAccount.getEmail(), jwtAccount.getPassword(), jwtAccount.getToken(), authorities);
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
