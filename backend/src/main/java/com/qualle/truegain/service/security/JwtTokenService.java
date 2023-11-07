package com.qualle.truegain.service.security;

import com.qualle.truegain.config.property.AuthenticationProperties;
import com.qualle.truegain.model.exception.TokenAuthenticationException;
import com.qualle.truegain.model.security.TokenClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.crypto.SecretKey;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JwtTokenService implements TokenService {

    private SecretKey key = null;
    private final AuthenticationProperties properties;

    @PostConstruct
    private void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getToken().getSecret()));
    }

    @Override
    public String generateAccess(TokenClaims claims) {

        return Jwts.builder()
                .id(claims.getTokenId())
                .claim("roles", claims.getRoles())
                .claim("uid", claims.getUserId())
                .subject(claims.getSubject())
                .issuer(claims.getIssuedBy())
                .notBefore(claims.getIssuedAt())
                .issuedAt(claims.getIssuedAt())
                .expiration(claims.getAccessExpiredAt())
                .signWith(key)
                .compact();
    }

    @Override
    public String generateRefresh(TokenClaims claims) {
        return Jwts.builder()
                .id(claims.getTokenId())
                .claim("session", claims.getSessionId())
                .claim("uid", claims.getUserId())
                .subject(claims.getSubject())
                .issuer(claims.getIssuedBy())
                .issuedAt(claims.getIssuedAt())
                .expiration(claims.getRefreshExpiredAt())
                .signWith(key)
                .compact();
    }

    @Override
    public TokenClaims validate(String token) {

        try {
            Claims payload = Jwts.parser()
                    .verifyWith(key).build()
                    .parseSignedClaims(token)
                    .getPayload();

            TokenClaims claims = TokenClaims.builder()
                    .subject(payload.getSubject())
                    .tokenId(payload.getId())
                    .issuedAt(payload.getIssuedAt())
                    .issuedBy(payload.getIssuer())
                    .userId(payload.get("uid", Long.class))
                    .build();

            if (payload.containsKey("roles")) {
                claims.setRoles(payload.get("roles", List.class));
                claims.setAccessExpiredAt(payload.getExpiration());
            }

            if (payload.containsKey("session")) {
                claims.setSessionId(payload.get("session", String.class));
                claims.setRefreshExpiredAt(payload.getExpiration());
            }

            return claims;
        } catch (ExpiredJwtException e) {
            throw new TokenAuthenticationException(e.getMessage(), e, true);

        } catch (JwtException e) {
            throw new TokenAuthenticationException(e.getMessage(), e);
        }

    }
}