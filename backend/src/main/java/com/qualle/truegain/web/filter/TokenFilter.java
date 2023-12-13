package com.qualle.truegain.web.filter;

import com.qualle.truegain.model.security.TokenClaims;
import com.qualle.truegain.model.security.TokenSecurityDetails;
import com.qualle.truegain.model.security.UserSecurityDetails;
import com.qualle.truegain.service.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final HandlerExceptionResolver resolver;

    public TokenFilter(TokenService tokenService, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.tokenService = tokenService;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String header = request.getHeader("Authorization");

        TokenClaims claims = null;

        try {
            if (header != null && header.startsWith("Bearer")) {
                String token = header.substring(7);
                claims = tokenService.validate(token);
            }

            if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                TokenSecurityDetails tokenDetails = new TokenSecurityDetails(claims);

                List<SimpleGrantedAuthority> roles = new ArrayList<>();

                if (claims.getRoles() != null) {
                    roles = claims.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(tokenDetails, null, roles);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}