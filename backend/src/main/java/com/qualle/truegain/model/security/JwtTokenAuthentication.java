package com.qualle.truegain.model.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtTokenAuthentication implements Authentication {

    private String token;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated;
    private TokenSecurityDetails principal;

    public JwtTokenAuthentication(String token) {
        this.token = token;
    }

    public JwtTokenAuthentication(String token, Collection<? extends GrantedAuthority> authorities, boolean authenticated, TokenSecurityDetails principal) {
        this.token = token;
        this.authorities = authorities;
        this.authenticated = authenticated;
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    public Long getId() {
        return principal.getId();
    }

    public String getToken() {
        return token;
    }
}
