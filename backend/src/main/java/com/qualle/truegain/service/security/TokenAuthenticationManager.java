package com.qualle.truegain.service.security;

import com.qualle.truegain.model.security.JwtTokenAuthentication;
import com.qualle.truegain.model.security.TokenClaims;
import com.qualle.truegain.model.security.TokenSecurityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationManager implements AuthenticationManager {

    private final TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        if (authentication instanceof JwtTokenAuthentication) {
            return processAuthentication((JwtTokenAuthentication) authentication);

        } else {
            authentication.setAuthenticated(false);
            return authentication;
        }
    }

    private JwtTokenAuthentication processAuthentication(JwtTokenAuthentication authentication) {
        String token = authentication.getToken();

        TokenClaims claims = tokenService.validate(token);

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        if (claims.getRoles() != null) {
            roles = claims.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }

        TokenSecurityDetails tokenDetails = new TokenSecurityDetails(claims);

        return new JwtTokenAuthentication(token, roles, true, tokenDetails);
    }
}
