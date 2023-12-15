package com.qualle.truegain.web.filter;

import com.qualle.truegain.model.security.JwtTokenAuthentication;
import com.qualle.truegain.model.security.TokenClaims;
import com.qualle.truegain.model.security.TokenSecurityDetails;
import com.qualle.truegain.service.security.TokenAuthenticationManager;
import com.qualle.truegain.service.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final HandlerExceptionResolver resolver;

    public TokenAuthenticationFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, TokenAuthenticationManager authenticationManager) {
        super("/private/**");
        this.resolver = resolver;
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
        setAuthenticationFailureHandler((request, response, authenticationException) -> {
            resolver.resolveException(request, response, null, authenticationException);
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer")) {
            String token = header.substring(7);

            JwtTokenAuthentication tokenAuthentication = new JwtTokenAuthentication(token);
            return getAuthenticationManager().authenticate(tokenAuthentication);
        }

        JwtTokenAuthentication authentication = new JwtTokenAuthentication(null);
        authentication.setAuthenticated(false);
        return authentication;
    }
}