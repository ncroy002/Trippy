package com.trippy.back.security;

import com.trippy.back.entities.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {
    private Account account = new Account();
    private Collection<? extends GrantedAuthority> authorities;
    public  JwtUserDetails(String email, String password, String token, List<GrantedAuthority> gratedAuthority){
        account.setEmail(email);
        account.setPassword(password);
        account.setToken(token);
        this.authorities = gratedAuthority;
    }

    //public static JwtUserDetails build(Acco)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
